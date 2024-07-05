package com.vino.info.example.synchronization;
import java.util.concurrent.locks.ReentrantLock;
//two threads (Thread 1 and Thread 2) attempt to acquire the lock using tryLock().
// If the lock is available, the thread enters the critical section.
// If not, it prints a message indicating that the lock couldn't be acquired.

//This approach is useful when you want to provide alternative logic or take specific actions when the lock is not available,
// rather than having the thread block until it can acquire the lock.
// It allows for more flexibility in handling scenarios where immediate lock acquisition is not critical.
public class SynchronizationExample4 {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            if (lock.tryLock()) {
                try {
                    System.out.println("Thread 1 acquired the lock");
                    // Critical section
                } finally {
                    lock.unlock();
                    System.out.println("Thread 1 released the lock");
                }
            } else {
                System.out.println("Thread 1 couldn't acquire the lock");
            }
        });

        Thread thread2 = new Thread(() -> {
            if (lock.tryLock()) {
                try {
                    System.out.println("Thread 2 acquired the lock");
                    // Critical section
                } finally {
                    lock.unlock();
                    System.out.println("Thread 2 released the lock");
                }
            } else {
                System.out.println("Thread 2 couldn't acquire the lock");
            }
        });

        // Start the threads
        thread1.start();
        thread2.start();
    }

}
