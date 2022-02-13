package me.java8to11.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class CompletableFutureApp {

    public static void difficultFuture(String[] args) throws InterruptedException, ExecutionException {

        // future로 하기 어렵던 작업들
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<String> future = executorService.submit(() -> "hello");

        // TODO

        future.get(); // 이거 하기 전까지는 아무것도 할 수 없다.
    }

    public static void asyncAndCallback(String[] args) throws InterruptedException, ExecutionException {
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

    public static void thenCompose(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        // hello 끝나고 world 해야한다.
        CompletableFuture<String> future = hello.thenCompose(s -> getWorld(s));
        System.out.println(future.get());

    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message + " World";
        });
    }

    public static void thenCombine(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        CompletableFuture<String> future = hello.thenCombine(world, (h, w) -> h + " " + w);
        System.out.println(future.get());
    }

    public static void combination(String[] args) throws InterruptedException, ExecutionException {
        // 조합하기
        // 여러 개 future 결과 list로 출력하기
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        List<CompletableFuture> futures = Arrays.asList(hello, world);
        CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

        CompletableFuture<List<Object>> future = CompletableFuture.allOf(futuresArray).thenApply(v -> {
            return futures.stream()
                    .map(f -> f.join())
                    .collect(Collectors.toList());
        });

        System.out.println(future.get());
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 예외처리 1: exceptionally
        boolean throwError = true;

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).exceptionally(ex -> {
            return "Error!";
        });

        System.out.println(hello.get());

        // 예외처리 2: handle
        hello = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).handle((result, ex) -> {
            if (ex != null) {
                System.out.println(ex);
                return "ERROR!";
            }
            return result;
        });

        System.out.println(hello.get());

    }

}
