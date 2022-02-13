package me.java8to11;

public class DefaultGreeting implements GreetingInterface, Bar {

    @Override
    public void printName() {
        System.out.println("DefaultGreeting");
    }

    // 충돌하는 경우에는 직접 overriding 해주어야 함
    @Override
    public void printNameUpperCase() {
        // 또 정의 가능
    }

    public static void main(String[] args) {
        GreetingInterface greetingInterface = new DefaultGreeting();
        greetingInterface.printName();

        GreetingInterface.printAnything(); // static method
    }
}
