package com.cognizant.week2.ex04;

public class MavenProjectDemo {

    public static void main(String[] args) {
        MavenProjectDemo demo = new MavenProjectDemo();
        int total = demo.add(12, 8);
        System.out.println("Maven project configured successfully");
        System.out.println("12 + 8 = " + total);
    }

    public int add(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }
}
