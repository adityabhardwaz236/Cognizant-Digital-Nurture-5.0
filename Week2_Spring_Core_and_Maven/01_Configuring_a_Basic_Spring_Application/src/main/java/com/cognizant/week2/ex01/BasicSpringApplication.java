package com.cognizant.week2.ex01;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BasicSpringApplication {

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            GreetingService greetingService = context.getBean(GreetingService.class);
            System.out.println(greetingService.greet("Spring Core"));
        }
    }

    public static class GreetingService {

        public String greet(String name) {
            return "Hello, " + name + "!";
        }
    }
}
