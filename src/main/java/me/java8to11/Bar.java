package me.java8to11;

// public interface Bar extends GreetingInterface {

// // default 구현체 제공하고 싶지 않음
// void printNameUpperCase();
// }

public interface Bar {
    default void printNameUpperCase() {
        System.out.println("GreetingInterFace");
    }
}
