package com.cognizant.week2.ex08;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

public class BasicAopApplication {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            CheckoutService checkoutService = context.getBean(CheckoutService.class);
            System.out.println(checkoutService.completePurchase("Headphones"));
        }
    }
}

@Configuration
@EnableAspectJAutoProxy
class AppConfig {

    @Bean
    public CheckoutService checkoutService() {
        return new CheckoutService();
    }

    @Bean
    public PerformanceAspect performanceAspect() {
        return new PerformanceAspect();
    }
}

class CheckoutService {

    public String completePurchase(String itemName) {
        return "Purchase completed for " + itemName;
    }
}

@Aspect
class PerformanceAspect {

    @Around("execution(* com.cognizant.week2.ex08.CheckoutService.*(..))")
    public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        long duration = System.nanoTime() - start;
        System.out.println(joinPoint.getSignature().getName() + " took " + duration + " ns");
        return result;
    }
}
