package myrpg.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    /* 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .cors(cors -> cors.disable())
            .csrf(csrf -> csrf.disable()) // Proper lambda to disable CSRF
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/**", "/static/**", "/index.html", "/assets/**", "/login", "/js/**").permitAll()
                .requestMatchers("/api/**").permitAll()
                .anyRequest().permitAll()// Require authentication for others
            ).sessionManagement(session -> session
            .sessionFixation().none() // Disables session fixation protection
            ) 
            .httpBasic(httpBasic -> {}) // Use an empty lambda body to enable HTTP Basic Authentication
            .build(); // Return the built SecurityFilterChain
    }
            */
            @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .cors(cors -> cors.disable())
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/", 
                "/index.html",
                "/favicon.ico",
                "/assets/**",
                "/media/**",
                "/*.*",
                "/static/**",
                "/**/*.js",
                "/*.js", 
                "/**/*.css", 
                "/*.css",
                "/**/*.png",
                 "/*.png",
                "/**/*.jpg",
                "/*.jpg",
                "/**/*.woff2",
                "/**/*.ttf",
                "/*.ttf",
                "/**"
            ).permitAll()
            .requestMatchers("/api/**").permitAll()
            .anyRequest().authenticated()  // Only protect sensitive routes
        )
        .httpBasic(httpBasic -> {})
        .build();
}

}
    




