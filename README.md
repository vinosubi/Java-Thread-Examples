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

1. The **ReentrantLock** class implements the Lock interface. This class is provided in java. util.concurrent package and was introduced in Java 1.5.
2. We have to create an object of the ReentrantLock class and the shared resource must be surrounded by lock and unlock methods on this object.
3. The thread that wants to execute the shared resource must take this lock and hence all the other threads trying to take a lock on this object must wait till the current thread releases it.
4. Now the beauty of ReentrantLock is, it allows the current thread to take the lock on the same lock object multiple times. Note that when the current thread first calls the lock() method, a hold count is set to one. This thread can call the lock() method again and every time hold count is incremented by one.
5. For every unlock request, the hold count is decremented by one and when the hold count is 0, the resource is unlocked.
   
![image](https://github.com/vinosubi/Java-Thread-Examples/assets/133937082/44e4050b-bbf3-45d7-b444-b0856bbf1669)

**Overview of ReentrantLock:**
1. The ReentrantLock class is a part of the Java Concurrency framework introduced in Java 5. It provides a flexible and powerful alternative to the traditional synchronized keyword for managing access to critical sections in a multithreaded environment. 
2. The term "Reentrant" in its name refers to its ability to allow a thread to re-acquire the lock it is holding, making it suitable for nested locking scenarios.

`Key Features:`

**1. Reentrancy:** A thread that already holds the lock can acquire it again without blocking.
**2. Interruptible Locking:** The lock acquisition can be interruptible using the lockInterruptibly() method.
**3. Fairness:** It supports both fair and unfair lock acquisition policies.
**4. Condition Support:** It provides support for creating associated Condition objects for advanced thread signaling.

**Here’s a basic overview of how to use ReentrantLock:**
`1. Import the ReentrantLock class:`

```java
import java.util.concurrent.locks.ReentrantLock;
```

`2. Create an instance of ReentrantLock:`

```java
ReentrantLock lock = new ReentrantLock();
```

`3. Use the lock and unlock methods to control access to the critical section:`

```java
try {
    lock.lock(); // Acquire the lock

    // Critical section - the code that needs to be synchronized
    // ...

} finally {
    lock.unlock(); // Release the lock in a finally block to ensure it's always released
}
```

The lock method is used to acquire the lock, and the unlock method is used to release it. It's important to release the lock in a finally block to ensure that it's released even if an exception occurs within the critical section.

`4. tryLock():`

You can also use the tryLock method, which attempts to acquire the lock without blocking. This method returns a boolean indicating whether the lock was acquired:
```java
if (lock.tryLock()) {
    try {
        // Critical section
        // ...
    } finally {
        lock.unlock();
    }
} else {
    // Handle the case where the lock couldn't be acquired immediately
    // ...
}
```

`5. tryLock(long timeout, TimeUnit unit)`

The tryLock(long timeout, TimeUnit unit) method in the ReentrantLock class provides a way to attempt to acquire the lock within a specified time duration. It is a variation of the tryLock() method that introduces a timeout parameter, allowing the thread to wait for a certain period for the lock to become available. If the lock is not acquired within the specified time, the method returns false.

`6. Reentrant behavior:`

As mentioned earlier, ReentrantLock is reentrant, meaning a thread can acquire the lock multiple times. To release the lock, the thread must call unlock the same number of times it called lock.

```java
lock.lock();   // First acquisition
try {
    // Critical section
    // ...
    lock.lock();   // Second acquisition by the same thread
    try {
        // Nested critical section
        // ...
    } finally {
        lock.unlock(); // Release the lock for the second acquisition
    }
} finally {
    lock.unlock(); // Release the lock for the first acquisition
}
```

`Example 1:`
**Let’s dive into a simple example to illustrate the basic of ReentrantLock:**
```java
import java.util.concurrent.locks.ReentrantLock;

public class Example1 {

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
```
In this example, two threads (Thread 1 and Thread 2) attempt to execute the performTask method. The ReentrantLock ensures that only one thread at a time can enter the critical section, demonstrating effective synchronization.

OUTPUT

```java
Thread 1 is in the critical section.
Thread 1: 0
Thread 1: 1
Thread 1: 2
Thread 1: 3
Thread 1: 4
Thread 1 is leaving the critical section.
Thread 2 is in the critical section.
Thread 2: 0
Thread 2: 1
Thread 2: 2
Thread 2: 3
Thread 2: 4
Thread 2 is leaving the critical section.
```

`Example 2:`

In the below example, two threads (Thread 1 and Thread 2) attempt to acquire the lock using tryLock(). If the lock is available, the thread enters the critical section. If not, it prints a message indicating that the lock couldn't be acquired.

This approach is useful when you want to provide alternative logic or take specific actions when the lock is not available, rather than having the thread block until it can acquire the lock. It allows for more flexibility in handling scenarios where immediate lock acquisition is not critical.

```java
package com.vino.info.example.synchronization;
import java.util.concurrent.locks.ReentrantLock;
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

```
The output of the provided code is not deterministic, and it can vary depending on the timing and scheduling of threads in a multithreaded environment. However, I can explain the possible outcomes:

**If Thread 1 acquires the lock successfully, you will see output like:**

```java
Thread 1 acquired the lock
Thread 2 couldn't acquire the lock
Thread 1 released the lock
```

**If Thread 2 acquires the lock successfully, you will see output like:**

```java
Thread 2 acquired the lock
Thread 1 couldn't acquire the lock
Thread 2 released the lock
```

**Thread 1 acquires the lock successfully, completes its critical section, releases the lock, and then Thread 2 acquires the lock successfully, completes its critical section, and releases the lock.**

```java
Thread 1 acquired the lock
Thread 1 released the lock
Thread 2 acquired the lock
Thread 2 released the lock
```
`Example 3`
An example using lock.tryLock() in a scenario with **employees** trying to access a shared resource (e.g., a report) without blocking each other:

```java
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class EmployeeExample {

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
```

### OUTPUT

```java
Alice acquired the lock
Alice modified the report to: Alice updated the report
Bob acquired the lock
Alice released the lock
Bob modified the report to: Bob updated the report
Bob released the lock
```


### In this example:

- Two employees, Alice and Bob, are represented by separate threads.
- Each employee attempts to acquire a lock to modify a shared report.
- If an employee cannot acquire the lock within 100 milliseconds, they print a message indicating they will try again later, allowing the thread to continue running without blocking.
- If an employee acquires the lock, they modify the report and release the lock after they are done.
- This approach ensures that the threads do not block indefinitely while waiting to acquire the lock and can remain responsive, potentially performing other tasks if the lock is not available.

- The run() method attempts to acquire the lock using lock.tryLock(100, TimeUnit.MILLISECONDS), which means it will try to acquire the lock and wait up to 100 milliseconds before giving up.

 ### Execution Flow
 
- Both threads (thread1 for Alice and thread2 for Bob) start almost simultaneously and attempt to acquire the lock.
- If Alice acquires the lock first, she modifies the report, releases the lock, and Bob will subsequently acquire the lock if he retries within the specified time.
- If neither can acquire the lock within the given 100 milliseconds, they will print a message indicating they couldn't acquire the lock and may take other actions or retry later.

#









