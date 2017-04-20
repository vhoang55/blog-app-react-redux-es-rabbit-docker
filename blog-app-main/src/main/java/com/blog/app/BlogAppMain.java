package com.blog.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;


@SpringBootApplication(scanBasePackages = { "com.blog.app"})
@IntegrationComponentScan
public class BlogAppMain {

    public static void main(String[] args) {
        SpringApplication.run(BlogAppMain.class, args);
    }

}
