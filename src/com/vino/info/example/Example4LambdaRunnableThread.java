package com.vino.info.example;

class MyThread3 implements Runnable
{
    public  void run()
    {
        System.out.println("Runnable Thread is running");
    }
}
public class Example4LambdaRunnableThread {

    public static void main(String[] args) {
        MyThread3 myThread3 = new MyThread3();
        Thread t1 = new Thread(myThread3);
        t1.start();
    }
}
