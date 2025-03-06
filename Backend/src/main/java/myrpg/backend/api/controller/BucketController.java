package myrpg.backend.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.awspring.cloud.s3.S3Template;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;



@RestController
@RequestMapping("/api/buckets")
public class BucketController {
    private final S3Client s3Client;
    private final S3Template s3Template;
    private static final String REGION = "us-east-2";
    private static final String BUCKET_NAME = "my.rpg.bucket";

    @Autowired
    public BucketController(S3Client s3Client, S3Template s3Template) {
        this.s3Client = s3Client;
        this.s3Template = s3Template;
    }

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
}