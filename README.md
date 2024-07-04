**What is a Thread?**
A thread is the smallest unit of execution within a process. In Java, multithreading is a powerful feature that allows multiple threads to run concurrently, providing a means to perform background tasks, handle multiple client requests, or improve performance through parallel processing.

**Creating Threads in Java**
There are two primary ways to create threads in Java:

By extending the Thread class.
'''
By implementing the Runnable interface.


**Thread Lifecycle**
A thread in Java goes through various states in its lifecycle:

**New:** A thread is in this state after being created but before the start method is invoked.
**Runnable:** The thread is ready to run and waiting for CPU time.
**Blocked:** The thread is waiting for a monitor lock to enter a synchronized block/method.
**Waiting:** The thread is waiting indefinitely for another thread to perform a particular action.
**Timed Waiting:** The thread is waiting for a specified amount of time.
**Terminated:** The thread has finished executing.

**Controlling Threads**
Java provides several methods to manage thread execution:

**start():** Begins the thread's execution.
**run():** Contains the code that constitutes the new thread.
**sleep(long millis):** Pauses the thread for the specified duration.
**join():** Waits for the thread to complete.
**yield():** Hints to the thread scheduler that the current thread is willing to yield its current use of a processor.
**interrupt(): ** Interrupts the thread, causing it to stop or perform an alternative action if it's in a waiting or sleeping state.

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

