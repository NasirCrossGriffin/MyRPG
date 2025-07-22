package myrpg.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		
		String environment = "";

		if (System.getenv("ENVIRONMENT") != null) {
			environment = System.getenv("ENVIRONMENT");
			System.setProperty("ENVIRONMENT", System.getenv("ENVIRONMENT"));
		} else {
			environment = "DEVELOPMENT";
		}
		
		System.out.println("MyRPG is running in " + environment + " mode.");


        // Set system properties for Spring Boot
        if (environment.equals("DEVELOPMENT")) {
			// Load .env file
			Dotenv dotenv = Dotenv.configure()
			.directory("./")  // Adjust the path to your .env file
			.load();

			System.setProperty("ENDPOINT", dotenv.get("DEVELOPMENT_ENDPOINT"));
			System.setProperty("DBUSERNAME", dotenv.get("DBUSERNAME"));
			System.setProperty("PASSWORD", dotenv.get("PASSWORD"));

			System.out.println("ENDPOINT: " + dotenv.get("DEVELOPMENT_ENDPOINT"));
			System.out.println("DBUSERNAME: " + dotenv.get("DBUSERNAME"));
			System.out.println("PASSWORD: " + dotenv.get("PASSWORD"));
		} else if (environment.equals("PRODUCTION")) {
			System.setProperty("ENDPOINT", System.getenv("PRODUCTION_ENDPOINT"));
			System.setProperty("DBUSERNAME", System.getenv("DBUSERNAME"));
			System.setProperty("PASSWORD", System.getenv("PASSWORD"));
					
			System.out.println("ENDPOINT: " + System.getenv("PRODUCTION_ENDPOINT"));
			System.out.println("DBUSERNAME: " + System.getenv("DBUSERNAME"));
			System.out.println("PASSWORD: " + System.getenv("PASSWORD"));
		}
		

		SpringApplication.run(BackendApplication.class, args);
		System.out.println("The backend is started! Welcome to springboot");
	}



}
