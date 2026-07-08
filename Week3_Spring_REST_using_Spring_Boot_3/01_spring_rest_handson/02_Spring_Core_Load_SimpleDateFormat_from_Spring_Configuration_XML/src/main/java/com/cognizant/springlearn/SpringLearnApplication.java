package com.cognizant.springlearn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SpringLearnApplication {

    public static void main(String[] args) throws Exception {
        new SpringLearnApplication().displayDate();
    }

    public void displayDate() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
        SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
        Date date = format.parse("31/12/2018");
        System.out.println(date);
    }
}
