package me.java8to11.completableFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsApp {

    public static void executorService(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor(); // 내부 Thread pool
        executorService.execute(() -> System.out.println("Thread: " + Thread.currentThread().getName()));
        executorService.shutdown(); // graceful shutdown: 끝까지 마치고 죽음
        executorService.shutdownNow(); // 그냥 지금 죽임
    }

    public static void newFixedThreadPool(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // blocking queue 에 쌓여 있다가 실행됨
        executorService.submit(getRunnable("hello"));
        executorService.submit(getRunnable("hello1"));
        executorService.submit(getRunnable("hello2"));
        executorService.submit(getRunnable("hello3"));
        executorService.shutdown();
    }

    // Runnable은 return을 못함, return 하려면..? -> Callable 사용해야 함
    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + " " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(getRunnable("hello"), 1, 2, TimeUnit.SECONDS);
        // executorService.shutdown();
    }

}
