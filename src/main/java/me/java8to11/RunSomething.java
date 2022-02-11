package me.java8to11;

@FunctionalInterface
public interface RunSomething {

    int doIt(int number); // 추상형 메서드가 1개 -> 함수형 인터페이스

    static void printName() {
        System.out.println("seongwon");
    }

    default void printAge() {
        System.out.println("28");
    }
}
