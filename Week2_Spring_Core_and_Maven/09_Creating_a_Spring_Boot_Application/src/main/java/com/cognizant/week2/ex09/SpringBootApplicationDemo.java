package com.cognizant.week2.ex09;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootApplicationDemo {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationDemo.class, args);
    }

    @Bean
    CommandLineRunner demoRunner() {
        return args -> System.out.println("Spring Boot application started successfully");
    }
}
