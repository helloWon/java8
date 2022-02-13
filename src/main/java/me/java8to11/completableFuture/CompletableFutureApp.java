package me.java8to11.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableFutureApp {

    public static void difficultFuture(String[] args) throws InterruptedException, ExecutionException {

        // future로 하기 어렵던 작업들
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<String> future = executorService.submit(() -> "hello");

        // TODO

        future.get(); // 이거 하기 전까지는 아무것도 할 수 없다.
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("seongwon");
        System.out.println(future.get());

        CompletableFuture<String> future1 = CompletableFuture.completedFuture("seongwon");

        // 비동기로 작업 실행하기 1
        // return type: void
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println("hello " + Thread.currentThread().getName());
        });

        // 비동기로 작업 실행하기 2
        // return type 있는 경우
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });
        System.out.println(future3.get());

        // 콜백 제공하기
        // async하게 callback
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply((s) -> { // thenAccept, thenRun
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });
        System.out.println(future4.get());
    }

}
