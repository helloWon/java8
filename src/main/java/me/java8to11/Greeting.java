package me.java8to11;

public class Greeting {

    private String name;

    public Greeting() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Greeting(String name) {
        this.setName(name);
    }

    // instance method
    public String hello(String name) {
        return "hello" + name;
    }

    public static String hi(String name) {
        return "hi" + name;
    }
}
