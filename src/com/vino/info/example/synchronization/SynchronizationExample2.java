package com.vino.info.example.synchronization;

class Counter2 {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class SyncThread extends Thread {
    private Counter2 counter;

    public SyncThread(Counter2 counter) {
        this.counter = counter;
    }

    public void run() {
        for (int i = 0; i < 20; i++) {
            counter.increment();
        }
    }
}

public class SynchronizationExample2 {
    public static void main(String[] args) {
        Counter2 counter = new Counter2();
        SyncThread t1 = new SyncThread(counter);
        SyncThread t2 = new SyncThread(counter);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final count: " + counter.getCount());
    }
}