
**Creating a Thread:** There are two main ways to create a thread in Java:

1.Extending the `Thread` class:

```java
class MyThread extends Thread {
    public void run() {
        // Code that will run in the new thread
        System.out.println("Thread is running");
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start(); // Start the new thread
    }
}

```

2.Implementing the `Runnable` interface:
```java
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
```

