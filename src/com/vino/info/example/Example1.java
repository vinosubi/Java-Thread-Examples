package com.vino.info.example;

//Creating a Thread: There are two main ways to create a thread in Java:
//1. Extending the Thread class:

class MyThread extends Thread
{
    public  void run()
    {
        System.out.println("Extending Thread is running");
    }
}
public class Example1 {

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();
    }
}
