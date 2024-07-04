package com.vino.info.example;

//2. Implementing the Runnable interface

class MyThread2 implements Runnable
{
    public  void run()
    {
        System.out.println("Runnable Thread is running");
    }
}
public class Example2 {

    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2();
        Thread t1 = new Thread(myThread2);
        t1.start();
    }
}
