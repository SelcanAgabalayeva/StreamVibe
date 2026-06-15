package com.selcan.StreamVibe.config;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {

    @PostConstruct
    public void init() {

        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

        setIfPresent(dotenv, "DB_HOST");
        setIfPresent(dotenv, "DB_PORT");
        setIfPresent(dotenv, "DB_NAME");
        setIfPresent(dotenv, "DB_USER");
        setIfPresent(dotenv, "DB_PASSWORD");
        setIfPresent(dotenv, "PORT");
    }

    private void setIfPresent(Dotenv dotenv, String key) {
        String value = dotenv.get(key);

        if (value != null && !value.isBlank()) {
            System.setProperty(key, value);
        }
    }
}