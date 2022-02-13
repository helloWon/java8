package me.java8to11;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Foo {
    public static void main(String[] args) {
        Function<Integer, Integer> plus10 = (i) -> i + 10;
        Function<Integer, Integer> multiply2 = (i) -> i * 2;
        System.out.println(plus10.apply(1));
        System.out.println(multiply2.apply(1));

        // higher order function
        Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(multiply2);
        System.out.println(multiply2AndPlus10.apply(2));

        System.out.println(plus10.andThen(multiply2).apply(2));

        // consumer
        Consumer<Integer> printT = (i) -> System.out.println(i);
        printT.accept(10);

        // supplier: return 값 주는거
        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10);

        // predicate: 어떤 인자 하나 받아서 bool return
        Predicate<String> startsWithSeongwon = (s) -> s.startsWith("seongwon");
        Predicate<Integer> isEven = (i) -> i % 2 == 0;

        // unaryOperator: 입출력 타입이 같은 경우
        UnaryOperator<Integer> multiply2_2 = (i) -> i * 2;

        // lambda expression
        Supplier<Integer> get100 = () -> 100;
        BinaryOperator<Integer> sum = (Integer a, Integer b) -> a + b; // type 생략 가능

        Foo foo = new Foo();
        foo.run();
    }

    private void run() {
        final int baseNumber = 10; // 변수 capture

        // 클래스들과 람다와 다른 점: 클래스들은 shadowing 가능 (별도의 scope이라 가능)
        // 람다는 같은 scope 사용

        // 로컬 클래스
        class LocalClass {
            void printBaseNumber() {
                System.out.println(baseNumber);
            }
        }

        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            public void accept(Integer integer) {
                System.out.println(baseNumber);
            }
        };

        // 람다
        IntConsumer printInt = (i) -> System.out.println(i + baseNumber);
        printInt.accept(10);
    }
}
