package myrpg.backend.config;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsConfig {

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.US_EAST_1) // Change to your AWS region
                .credentialsProvider(DefaultCredentialsProvider.create())
                .endpointOverride(URI.create("https://s3.us-east-1.amazonaws.com"))
                .build();
    }
}
