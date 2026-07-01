package com.cognizant.week2.ex05;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIocContainerApplication {

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            ContainerInfo containerInfo = context.getBean(ContainerInfo.class);
            System.out.println(containerInfo.describe());
        }
    }

    public static class ContainerInfo {

        private String framework;
        private String purpose;

        public String getFramework() {
            return framework;
        }

        public void setFramework(String framework) {
            this.framework = framework;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public String describe() {
            return framework + " manages beans for " + purpose;
        }
    }
}
