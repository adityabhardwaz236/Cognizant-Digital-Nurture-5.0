package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SpringLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) throws Exception {
        new SpringLearnApplication().displayDate();
    }

    public void displayDate() throws Exception {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
        SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
        Date date = format.parse("31/12/2018");
        LOGGER.debug("Date parsed: {}", date);
        LOGGER.info("END");
    }
}
