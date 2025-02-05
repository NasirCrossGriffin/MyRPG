package myrpg.backend.api.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.Bucket;
import java.util.List;
import java.io.*;
import io.awspring.cloud.s3.S3Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/buckets")
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
            String fileUrl = String.format("https://%s.s3.%s.amazonaws.com/%s", 
                                           "my.rpg.bucket", "us-east-1", objectKey);
            return fileUrl;
        } catch (IOException e) {
            return "File upload failed: " + e.getMessage();
        }
    }
}