package com.stevesouza.cameldemo;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class CameldemoApplication {
    @Value("${spring.data.mongodb.host}")
    private String mongoServerUrl;

    @Value("${spring.data.mongodb.port}")
    private int mongoServerPort;

    public static void main(String[] args) {
        SpringApplication.run(CameldemoApplication.class, args);
    }

    @Bean("myDb")
    public MongoClient mongo() {
        return new MongoClient(mongoServerUrl, mongoServerPort);
    }

}
