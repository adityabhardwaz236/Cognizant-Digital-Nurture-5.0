package com.cognizant.springlearn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringLearnApplication {

    public static void main(String[] args) {
        new SpringLearnApplication().displayCountries();
    }

    public void displayCountries() {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countryList = context.getBean("countryList", List.class);
        countryList.forEach(System.out::println);
    }
}
