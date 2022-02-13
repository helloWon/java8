package me.java8to11;

public interface GreetingInterface {

    void printName();

    // default keyword: 하위 클래스에서 따로 구현 안해도 됨
    default void printNameUpperCase() {
        System.out.println("GreetingInterFace");
    }

    static void printAnything() {
        System.out.println("Foo");
    }
}
