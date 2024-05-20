package com.bloggios.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BloggiosEmailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloggiosEmailServiceApplication.class, args);
    }

}
