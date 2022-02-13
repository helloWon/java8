package me.java8to11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Stream {

    public static void main(String[] args) {
        // stream: 연속된 data를 처리하는 operator의 모음

        List<String> names = new ArrayList<>();
        names.add("seongwon");
        names.add("one");
        names.add("two");
        names.add("three");

        java.util.stream.Stream<String> stringStream = names.stream().map(String::toUpperCase);
        names.forEach(System.out::println); // data는 바뀌지 않음
        stringStream.forEach(System.out::println);

        // 뒤에 붙는 메소드는 크게 2가지: 중개 operator(LAZY, stream return), 종료 operator
        names.stream().map((s) -> {
            System.out.println(s); // 출력안됨
            return s.toUpperCase(); // 종료형 operator가 없어서
        });

        System.out.println("==============");

        List<String> collect = names.stream().map((s) -> {
            System.out.println(s); // 출력됨
            return s.toUpperCase(); // 종료형 operator가 있어서
        }).collect(Collectors.toList());
        collect.forEach(System.out::println);

        // 병렬처리
        for (String name : names) {
            if (name.startsWith("S"))
                System.out.println(name.toUpperCase());
        }

        List<String> collect2 = names.parallelStream().map(String::toUpperCase).collect(Collectors.toList());
        collect2.forEach(System.out::println);

        List<String> collect3 = names.parallelStream().map((s) -> { // 무조건 다 빨라지는거 아님, 데이터가 정말 큰 경우 유용
            System.out.println(s + " " + Thread.currentThread().getName()); // stream이 다름
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collect3.forEach(System.out::println);

    }
}
