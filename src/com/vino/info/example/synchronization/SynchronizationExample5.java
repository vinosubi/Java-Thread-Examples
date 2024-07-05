package com.vino.info.example.synchronization;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
//example using lock.tryLock() in a scenario with employees trying to access a shared resource (e.g., a report) without blocking each other:
public class SynchronizationExample5 {

    private static final ReentrantLock lock = new ReentrantLock();
    private static String report = "Initial Report";

    public static void main(String[] args) {
        Employee employee1 = new Employee("Alice");
        Employee employee2 = new Employee("Bob");

        Thread thread1 = new Thread(employee1);
        Thread thread2 = new Thread(employee2);

        thread1.start();
        thread2.start();
    }

    static class Employee implements Runnable {
        private final String name;

        public Employee(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println(name + " acquired the lock");
                        // Simulate modifying the report
                        report = name + " updated the report";
                        System.out.println(name + " modified the report to: " + report);
                    } finally {
                        lock.unlock();
                        System.out.println(name + " released the lock");
                    }
                } else {
                    System.out.println(name + " couldn't acquire the lock. Will try again later.");
                    // Perform alternative actions here
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
