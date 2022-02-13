package me.java8to11.furthermore;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ParallelArrayApp {
    public static void main(String[] args) {
        int size = 1500;
        int[] numbers = new int[size];
        Random random = new Random();
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());

        long start = System.nanoTime();
        Arrays.sort(numbers); // thread 하나만 사용
        System.out.println("serial sorting took " + (System.nanoTime() - start));

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        start = System.nanoTime();
        Arrays.parallelSort(numbers); // divide & conquer 방식으로 정렬, merge sort: worst o(n^2)
        System.out.println("parallel sorting took " + (System.nanoTime() - start));
    }
}
