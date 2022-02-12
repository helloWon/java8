package me.java8to11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {
    public static void main(String[] args) {
        Function<Integer, String> intToString = (i) -> "number";

        // UnaryOperator: 입출력 type 동일
        UnaryOperator<String> hi = Greeting::hi; // colon 2개 -> 메소드 레퍼런스 사용한 거

        // new
        Supplier<Greeting> newGreeting = Greeting::new; // 참조만 했지 아직 아무일도 일어나지 않음
        newGreeting.get();

        // name
        Function<String, Greeting> seongwonGreeting = Greeting::new; // name
        Greeting seongwon = seongwonGreeting.apply("seongwon");
        System.out.println(seongwon.getName());

        String[] names = { "seongwon", "elmo", "toby" };
        Arrays.sort(names, String::compareToIgnoreCase); // comparator: 함수형 인터페이스, 람다로 변경 가능, 메소드 레퍼런스 사용 가능
        System.out.println(Arrays.toString(names));
    }
}
