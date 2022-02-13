package me.java8to11.furthermore;

import java.util.Arrays;
import java.util.List;

@Chicken("양념")
@Chicken("마늘간장")
public class App {

    public static void main(String[] args) {
        // List<@Chicken String> names = Arrays.asList("seongwon");

        // 호출
        Chicken[] chickens = App.class.getDeclaredAnnotationsByType(Chicken.class);
        Arrays.stream(chickens).forEach(c -> {
            System.out.println(c.value());
        });

        // 컨테이너 사용해서 호출
        ChickenContainer chickenContainer = App.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value()).forEach(c -> {
            System.out.println(c.value());
        });
    }

    // static class FeelsLikeChicken<@Chicken T> {
    // // type parameter, type
    // public static <@Chicken C> void print(C c) {

    // }
    // }
}
