package com.cognizant.week2.ex06;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class AnnotationBeansApplication {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            NotificationService notificationService = context.getBean(NotificationService.class);
            System.out.println(notificationService.send("Spring annotations work"));
        }
    }
}

@Configuration
class AppConfig {

    @Bean
    public NotificationService notificationService() {
        return new NotificationService(new SmsGateway());
    }
}

class NotificationService {

    private final SmsGateway smsGateway;

    NotificationService(SmsGateway smsGateway) {
        this.smsGateway = smsGateway;
    }

    String send(String message) {
        return smsGateway.send(message);
    }
}

class SmsGateway {

    String send(String message) {
        return "Message sent: " + message;
    }
}
