package com.vino.info.example;

public class Example3LambdaThread {
    public static void main(String[] args) {
        // Creating a thread using a lambda expression
        Thread thread = new Thread(() -> {
            System.out.println("Thread is running");
        });

        // Start the thread
        thread.start();
    }
}