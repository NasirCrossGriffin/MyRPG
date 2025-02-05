package myrpg.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		// Load .env file
        Dotenv dotenv = Dotenv.configure()
            .directory("./")  // Adjust the path to your .env file
            .load();

        // Set system properties for Spring Boot
        
        System.setProperty("ENDPOINT", dotenv.get("ENDPOINT"));
        System.setProperty("DBUSERNAME", dotenv.get("DBUSERNAME"));
        System.setProperty("PASSWORD", dotenv.get("PASSWORD"));
		System.setProperty("AWS_SECRET_ACCESS_KEY", dotenv.get("AWS_SECRET_ACCESS_KEY"));
		System.setProperty("AWS_ACCESS_KEY_ID", dotenv.get("AWS_ACCESS_KEY_ID"));
		System.setProperty("aws.accessKeyId",  dotenv.get("AWS_ACCESS_KEY_ID"));
		System.setProperty("aws.secretAccessKey", dotenv.get("AWS_SECRET_ACCESS_KEY"));
		
		System.out.println("ENDPOINT: " + dotenv.get("ENDPOINT"));
		System.out.println("DBUSERNAME: " + dotenv.get("DBUSERNAME"));
		System.out.println("PASSWORD: " + dotenv.get("PASSWORD"));
		System.out.println("AWS_ACCESS_KEY_ID (System.getProperty): " + System.getProperty("aws.accessKeyId"));
		System.out.println("AWS_SECRET_ACCESS_KEY (System.getProperty): " + System.getProperty("aws.secretAccessKey"));
		

		SpringApplication.run(BackendApplication.class, args);
		System.out.println("The backend is started! Welcome to springboot");
	}

}
