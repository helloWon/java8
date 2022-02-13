package me.java8to11.completableFuture;

public class ConcurrentApp {
    /**
     * 자바에서 지원하는 concurrent 프로그래밍
     * 멀티프로세싱 (ProcessBuilder)
     * 멀티쓰레드 <- 여기서 살펴볼 것은 Thread
     * 
     * @throws InterruptedException
     */

    // // 1번 방법: Thread 상속
    // public static void main(String[] args) {
    // MyThread myThread = new MyThread();
    // myThread.start();

    // System.out.println("Hello: " + Thread.currentThread().getName());
    // }

    // static class MyThread extends Thread {
    // @Override
    // public void run() {
    // System.out.println("Thread: " + Thread.currentThread().getName());
    // }
    // }

    // // 2번 방법: Runnable 사용
    // public static void main(String[] args) {
    // Thread thread = new Thread(new Runnable() {
    // @Override
    // public void run() {
    // System.out.println("Thread: " + Thread.currentThread().getName());
    // }
    // });
    // thread.start();

    // System.out.println("Hello: " + Thread.currentThread().getName());
    // }

    // 3번 방법: lambda 사용
    public static void main(String[] args) throws InterruptedException {
        /**
         * Thread 주요 기능 3가지: sleep, interrupt, join
         * 수십, 수백개의 thread를 개발자가 직접 관리하는 것은 어렵다. -> Executors 탄생 배경
         */

        Thread thread = new Thread(() -> {

            while (true) {
                System.out.println("Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) { // sleep: 자는 동안 깨우면 실행됨
                    // System.out.println("exit!");
                    // return;
                }
            }
        });
        thread.start();

        System.out.println("Hello: " + Thread.currentThread().getName());
        // Thread.sleep(3000L);
        // thread.interrupt(); // interrupt: thread 깨움

        thread.join(); // join: 기다림
        System.out.println(thread + " is finished");
    }
}
