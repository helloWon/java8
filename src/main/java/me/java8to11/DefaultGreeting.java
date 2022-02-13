package me.java8to11;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;

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

        List<String> name = new ArrayList<>();
        name.add("seongwon");
        name.add("one");
        name.add("two");
        name.add("three");

        // java8 - forEach
        name.forEach(System.out::println);

        // java8 - spliterator
        Spliterator<String> spliterator = name.spliterator();
        Spliterator<String> spliterator1 = spliterator.trySplit();
        while (spliterator.tryAdvance(System.out::println))
            ;
        System.out.println("=================");
        while (spliterator1.tryAdvance(System.out::println))
            ;

        // java8 - stream
        long result = name.stream().map(String::toUpperCase).filter(s -> s.startsWith("S")).count();
        System.out.println(result);

        // java8 - removeif
        name.removeIf(s -> s.startsWith("s"));
        name.forEach(System.out::println);

        // java8 - comparator
        System.out.println("=================");
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        name.sort(compareToIgnoreCase.reversed()); // thenComparing()
        name.forEach(System.out::println);

    }
}
