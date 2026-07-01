package com.cognizant.week2.ex07;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InjectionStylesApplication {

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            Instructor instructor = context.getBean("instructor", Instructor.class);
            TrainingSession trainingSession = context.getBean("trainingSession", TrainingSession.class);
            System.out.println(instructor.describe());
            System.out.println(trainingSession.describe());
        }
    }

    public static class Instructor {

        private final String name;
        private final String topic;

        public Instructor(String name, String topic) {
            this.name = name;
            this.topic = topic;
        }

        public String describe() {
            return name + " teaches " + topic;
        }
    }

    public static class TrainingSession {

        private String title;
        private int durationHours;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDurationHours(int durationHours) {
            this.durationHours = durationHours;
        }

        public String describe() {
            return title + " lasts " + durationHours + " hours";
        }
    }
}
