package me.java8to11;

public class Foo {
    public static void main(String[] args) {

        // 익명 내부 클래스 anonymous inner class & lambda 표현식
        RunSomething runSomething = (number) -> {
            return number + 10;
        };

        // 같은 값을 넣었을 때 항상 같은 결과 -> 순수한 함수, 상태값 의존 X
        System.out.println(runSomething.doIt(1));
        System.out.println(runSomething.doIt(1));
        System.out.println(runSomething.doIt(1));
    }
}
