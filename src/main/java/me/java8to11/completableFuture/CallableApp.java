package me.java8to11.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableApp {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor(); // 내부 Thread pool

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "java";
        };

        Callable<String> sample = () -> {
            Thread.sleep(1000L);
            return "sample";
        };

        // Future<String> helloFuture = executorService.submit(hello);
        // System.out.println(helloFuture.isDone());
        // System.out.println("Started!");

        // helloFuture.get(); // 결과값을 가져올때까지 기다림. blocking
        // helloFuture.cancel(false); // 취소, 이제 값 못가져옴

        // System.out.println(helloFuture.isDone());
        // executorService.shutdown();

        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, sample));
        for (Future<String> f : futures) {
            System.out.println(f.get());
        } // invokeAll은 다 끝날때까지 기다리기 때문에 한꺼번에 출력됨
          // 바로 주고 싶다면 invokeAny 사용하도록 한다.

        executorService.shutdown();

    }
}
