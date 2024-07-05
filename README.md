**What is a Thread?** 
A thread is the smallest unit of execution within a process. In Java, multithreading is a powerful feature that allows multiple threads to run concurrently, providing a means to perform background tasks, handle multiple client requests, or improve performance through parallel processing.

**Creating Threads in Java**
There are two primary ways to create threads in Java:

By extending the Thread class.
\
By implementing the Runnable interface.


**Thread Lifecycle**
A thread in Java goes through various states in its lifecycle:

**New:** A thread is in this state after being created but before the start method is invoked.\
**Runnable:** The thread is ready to run and waiting for CPU time.\
**Blocked:** The thread is waiting for a monitor lock to enter a synchronized block/method.\
**Waiting:** The thread is waiting indefinitely for another thread to perform a particular action.\
**Timed Waiting:** The thread is waiting for a specified amount of time.\
**Terminated:** The thread has finished executing.

**Controlling Threads**
Java provides several methods to manage thread execution:

**start():** Begins the thread's execution.\
**run():** Contains the code that constitutes the new thread.\
**sleep(long millis):** Pauses the thread for the specified duration.\
**join():** Waits for the thread to complete.\
**yield():** Hints to the thread scheduler that the current thread is willing to yield its current use of a processor.\
**interrupt():** Interrupts the thread, causing it to stop or perform an alternative action if it's in a waiting or sleeping state.

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
**Lambda Thread**

`Example 3 : Basic Lambda Thread`
```java
public class LambdaThreadExample {
    public static void main(String[] args) {
        // Creating a thread using a lambda expression
        Thread thread = new Thread(() -> {
            System.out.println("Thread is running");
        });
        
        // Start the thread
        thread.start();
    }
}
```


`Example 4 : Lambda Thread with Runnable Interface`
```java
public class LambdaRunnableExample {
    public static void main(String[] args) {
        // Implementing Runnable using a lambda expression
        Runnable runnable = () -> {
            System.out.println("Runnable is running");
        };
        
        // Creating a thread with the Runnable instance
        Thread thread = new Thread(runnable);
        
        // Start the thread
        thread.start();
    }
}
```

`Example 5 : Lambda Thread with a Parameterized Runnable`
```java
public class LambdaParameterizedRunnableExample {
    public static void main(String[] args) {
        // Parameter to be passed to the thread
        String message = "Parameterized Runnable is running";
        
        // Implementing Runnable using a lambda expression with a parameter
        Runnable runnable = () -> {
            System.out.println(message);
        };
        
        // Creating a thread with the Runnable instance
        Thread thread = new Thread(runnable);
        
        // Start the thread
        thread.start();
    }
}

```

`Example 6 : Lambda Thread Performing a Task`
```java
public class LambdaTaskExample {
    public static void main(String[] args) {
        // Task to be performed by the thread
        Runnable task = () -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Task: " + i);
            }
        };
        
        // Creating a thread with the Runnable task
        Thread thread = new Thread(task);
        
        // Start the thread
        thread.start();
    }
}

```

`Example 7 : Lambda Thread with Exception Handling`
```java
public class LambdaExceptionHandlingExample {
    public static void main(String[] args) {
        // Task to be performed by the thread with exception handling
        Runnable task = () -> {
            try {
                System.out.println("Thread is running");
                // Simulating an exception
                throw new Exception("Exception in thread");
            } catch (Exception e) {
                System.out.println("Caught exception: " + e.getMessage());
            }
        };
        
        // Creating a thread with the Runnable task
        Thread thread = new Thread(task);
        
        // Start the thread
        thread.start();
    }
}
```



#
**Thread Synchronization**\
When multiple threads access shared resources, synchronization is essential to prevent data inconsistencies. The synchronized keyword ensures that only one thread can execute a synchronized block or method at a time.

**Synchronization** in Java ensures that only one thread can access a shared resource or critical section at a time, preventing data corruption and inconsistencies.

`This is usually achieved by following the below steps:`
1. New thread comes and acquires a lock on the shared object/class. The thread performs the required operation while the other incoming threads patiently wait for their turn.
2. The thread releases the acquired lock.
3. Same steps start from step 1 for other threads.

   ![image](https://github.com/vinosubi/Java-Thread-Examples/assets/133937082/28b76846-1774-4f83-835b-7310e7495527)

**Why use synchronization?**
If multiple threads try to simultaneously access and modify the shared data, it can lead to data corruption. Inconsistent or incorrect values can be read or written, causing unexpected behaviour and incorrect program output.

As discussed in the above example, if multiple people try to access the printer simultaneously without synchronization, they might end up sending their print jobs at the same time. This can lead to conflicts where print jobs get mixed up, pages are printed out of order, or multiple print jobs are overlapped on the same paper.

**How to use synchronization?**
At a high level, there are two ways by which you can achieve synchronization using the synchronized keyword:

`Synchronized Methods:` When a thread invokes a synchronized method, it acquires the lock associated with the object instance on which the method is called. Other threads trying to access synchronized methods on the same object instance will be blocked until the lock is released.

```java
public synchronized void synchronizedMethod() {
    // Code that requires synchronization
}
```

`Synchronized Blocks:` You can use synchronized blocks to enclose a specific section of code that needs to be synchronized. A synchronized block is defined using the synchronized keyword followed by the object instance or class used for synchronization. Only one thread can execute the code within a synchronized block at a time.
```java
public void someMethod() {
    // Code executed without synchronization

    synchronized (sharedObject) {
        // Code that requires synchronization
    }

    // Code executed without synchronization
}
```

'Now, Consider the following example for understanding the internal working of the synchronized keyword:'

```java
public class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized void decrement() {
        count--;
    }

    public synchronized int getCount() {
        return count;
    }
}
```

In this example, we have a class named Counter that maintains an internal count variable. The increment, decrement, and getCount methods are all marked as synchronized(only considered for explaining the example).

1. Multiple threads can operate concurrently on a single instance of the Counter class.
2. When a thread calls the increment method on a Counter instance, it attempts to acquire the lock associated with that instance's monitor.
3. If the lock is available, the thread acquires the lock, enters the monitor, and executes the critical section of the increment method, which increments the count variable by 1.
4. While a thread is inside the synchronized increment method, other threads that try to call either the increment, decrement, or getCount methods on the same Counter instance are blocked, as they need to acquire the lock associated with the monitor.
5. Once the thread finishes executing the critical section (i.e., incrementing the count), it releases the lock, allowing other waiting threads to acquire the lock and execute their synchronized methods.
6. Similarly, when threads call the synchronized decrement or getCount methods, they follow the same steps of acquiring and releasing the lock associated with the monitor, ensuring that only one thread executes the critical section at a time.

   
`Example 1`
```java
class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class SynchronizationExample1 {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                counter.increment();
                System.out.println("Thread-1 is Executing : " + counter.getCount());
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                counter.increment();
                System.out.println("Thread-2 is Executing : " + counter.getCount());
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count: " + counter.getCount()); // Expected output: 2000
    }
}
```

`Example 2`
```java
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
```
We have already learned how to synchronize a shared resource using a **synchronized** keyword. Now in this diccussion, we’ll see a better way to synchronize shared resources using **ReentrantLock**

1. The ReentrantLock class implements the Lock interface. This class is provided in java. util.concurrent package and was introduced in Java 1.5.
2. We have to create an object of the ReentrantLock class and the shared resource must be surrounded by lock and unlock methods on this object.
3. The thread that wants to execute the shared resource must take this lock and hence all the other threads trying to take a lock on this object must wait till the current thread releases it.
4. Now the beauty of ReentrantLock is, it allows the current thread to take the lock on the same lock object multiple times. Note that when the current thread first calls the lock() method, a hold count is set to one. This thread can call the lock() method again and every time hold count is incremented by one.
5. For every unlock request, the hold count is decremented by one and when the hold count is 0, the resource is unlocked.
