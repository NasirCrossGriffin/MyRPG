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

		System.out.println("ENDPOINT: " + dotenv.get("ENDPOINT"));
		System.out.println("DBUSERNAME: " + dotenv.get("DBUSERNAME"));
		System.out.println("PASSWORD: " + dotenv.get("PASSWORD"));

		SpringApplication.run(BackendApplication.class, args);
		System.out.println("The backend is started! Welcome to springboot");
	}

}
