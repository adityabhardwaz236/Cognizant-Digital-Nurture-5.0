package com.cognizant.week2.ex02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class DependencyInjectionApplication {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            OrderService orderService = context.getBean(OrderService.class);
            System.out.println(orderService.placeOrder("Laptop"));
        }
    }
}

@Configuration
class AppConfig {

    @Bean
    public NotificationService notificationService() {
        return new EmailNotificationService();
    }

    @Bean
    public OrderService orderService(NotificationService notificationService) {
        return new OrderService(notificationService);
    }
}

class OrderService {

    private final NotificationService notificationService;

    public OrderService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public String placeOrder(String itemName) {
        return "Order placed for " + itemName + " | " + notificationService.notifyCustomer(itemName);
    }
}

interface NotificationService {

    String notifyCustomer(String itemName);
}

class EmailNotificationService implements NotificationService {

    @Override
    public String notifyCustomer(String itemName) {
        return "Email sent for " + itemName;
    }
}
