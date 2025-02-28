package myrpg.backend.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

@Configuration
public class CookieConfig implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Configure session cookie properties here
        servletContext.getSessionCookieConfig().setName("MYRPG_SESSION_COOKIE");
        servletContext.getSessionCookieConfig().setHttpOnly(true);
        servletContext.getSessionCookieConfig().setSecure(true); // Ensure this is only true if you're using HTTPS
        servletContext.getSessionCookieConfig().setPath("/");

        // You can also configure other session settings here if needed
    }
}

