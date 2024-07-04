package com.vino.info.example.synchronization;

class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class SynchronizationExample1 {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                counter.increment();
                System.out.println("Thread-1 is Executing : " + counter.getCount());
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                counter.increment();
                System.out.println("Thread-2 is Executing : " + counter.getCount());
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count: " + counter.getCount()); // Expected output: 2000
    }
}
