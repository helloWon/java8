package me.java8to11;

import java.util.function.Consumer;
import java.util.function.Function;
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

    }
}
