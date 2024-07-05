package com.vino.info.example.synchronization;

import java.util.concurrent.locks.ReentrantLock;

public class SynchronizationExample3 {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        // Create multiple threads to demonstrate locking
        Thread thread1 = new Thread(() -> performTask("Thread 1"));
        Thread thread2 = new Thread(() -> performTask("Thread 2"));
        // Start the threads
        thread1.start();
        thread2.start();
    }

    private static void performTask(String threadName) {
        // Acquire the lock
        lock.lock();

        try {
            System.out.println(threadName + " is in the critical section.");

            // Simulate some work in the critical section
            for (int i = 0; i < 5; i++) {
                System.out.println(threadName + ": " + i);
                try {
                    Thread.sleep(100); // Simulate some processing time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } finally {
            // Release the lock in a finally block to ensure it's released even if an exception occurs
            System.out.println(threadName + " is leaving the critical section.");
            lock.unlock();
        }
    }
}
