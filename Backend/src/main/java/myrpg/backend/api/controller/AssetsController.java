package myrpg.backend.api.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/api/assets")
public class AssetsController {

    @Autowired
    public AssetsController() {

    }
    
    /* 
        @GetMapping("/list")
        public String[] listBuckets() {
        ListBucketsResponse response = s3Client.listBuckets();
        List<String> bucketNames = response.buckets().stream()
                                        .map(Bucket::name)
                                        .collect(Collectors.toList());
        return bucketNames.toArray(new String[0]); // Convert List<String> to String[]
        }

        @PostMapping("/insert")
        public String insertFile(@RequestParam("file") MultipartFile file) {
            try {
                String objectKey = file.getOriginalFilename();
                s3Template.upload("my.rpg.bucket", objectKey, file.getInputStream());
                
                // Use virtual-hosted-style URL
                String fileUrl = String.format("https://s3.%s.amazonaws.com/%s/%s", 
                                        "us-east-1", "my.rpg.bucket", objectKey);
                return fileUrl;
            } catch (IOException e) {
                return "File upload failed: " + e.getMessage();
            }
        }
    */

    public ResponseEntity<String> uploadFileLocally(MultipartFile file) {
        String uploadDir = "/portfolio/MyRPG/assets";
        String fileName = file.getOriginalFilename();

        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            file.transferTo(filePath.toFile());

            // Return accessible URL
            String fileUrl = "https://myrpgapp.com/assets/" + fileName;
            return ResponseEntity.ok(fileUrl);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
        }
    }

    public String uploadToProductionServer(MultipartFile file) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        });

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        String uploadUrl = "https://myrpgapp.com/api/assets/insert";
        ResponseEntity<String> response = restTemplate.postForEntity(uploadUrl, requestEntity, String.class);

        return response.getBody(); // e.g. "/assets/myImage.jpg"
    }

    @PostMapping("/insert")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (System.getProperty("ENVIRONMENT") == "PRODUCTION") {
            return uploadFileLocally(file);
        } else {
            String fileURL = "";
            try {
                fileURL = uploadToProductionServer(file);
            } catch (Exception e) {                
                return ResponseEntity.status(HttpStatusCode.valueOf(500)).body("Failed to upload assets");
            }

            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(fileURL);
        }
    }
}