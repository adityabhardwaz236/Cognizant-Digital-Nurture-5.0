package com.cognizant.week2.ex03;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

public class SpringAopLoggingApplication {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            AccountService accountService = context.getBean(AccountService.class);
            System.out.println(accountService.deposit(500));
            System.out.println(accountService.withdraw(200));
        }
    }
}

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = SpringAopLoggingApplication.class)
class AppConfig {

    @Bean
    public AccountService accountService() {
        return new AccountService();
    }

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}

class AccountService {

    public String deposit(int amount) {
        return "Deposited " + amount;
    }

    public String withdraw(int amount) {
        return "Withdrew " + amount;
    }
}

@Aspect
class LoggingAspect {

    @Before("execution(* com.cognizant.week2.ex03.AccountService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Starting: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* com.cognizant.week2.ex03.AccountService.*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        System.out.println("Completed: " + joinPoint.getSignature().getName() + " -> " + result);
    }
}
