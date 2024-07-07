# ExecutorService

`ExecutorService:`

**Whats exactly concurrency ?**
Concurrency is the ability of a system to manage multiple tasks simultaneously. It is essential for improving the performance and responsiveness of the system.

![image](https://github.com/vinosubi/Java-Thread-Examples/assets/133937082/ac0dcb21-41f1-446c-8282-5298770a66b8)

An **ExecutorService** is a utility in Java that provides a way to execute tasks concurrently.
It manages a pool of worker threads, and allows you to submit tasks for execution.
The ExecutorService handles creation, management, and reusability of threads, making it easier to handle concurrent tasks in multithreaded applications.

![image](https://github.com/vinosubi/Java-Thread-Examples/assets/133937082/b57765f9-cdbf-4017-9f80-5433ddf6a8ea)

Starting from Java 5, JDK provides API ExecutorService that simplifies running tasks in asynchronous mode. It provides a pool of threads and an API to assign tasks to it.

ExecutorService is an interface provided by JDK. Its object can be obtained using class Executors. There are 5 types of thread pools we can create

```java
1. ExecutorService execService = Executors.newSingleThreadExecutor(10);
 ```
 Creates an Executor that uses a single thread
 
 **SingleThreadedExecutor :** It is similar to a fixed thread executor, but the size of the blocking queue is 1. In this case if the thread is killed because of an exception, a new thread is created. It is used when we want to make sure of the order of execution (sequentially).

```java
2. ExecutorService execService = Executors.newFixedThreadPool(10);
```
 Creates a thread pool that reuses a fixed number of threads
 
**Fixed Thread Pool:** We have fixed number of threads which pick up tasks assigned to it. All tasks are stored in a thread safe blocking queue.


```java
3. ExecutorService execService = Executors.newScheduledThreadPool(10);
```
 Creates a thread pool that can schedule commands to run after a given delay, or to execute periodically.
 
**ScheduledThreadPool:** It is used for tasks which need to be scheduled after a delay. We can configure a one-off delay, or a periodic schedule, or even a schedule with a fixed delay. A delay queue is used here, because of which the order of tasks is not in order.


```java
4. ExecutorService execService = Executors.newCachedThreadPool();
```
 Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available.
 
**CachedThreadPool:** We do not have a fixed number of threads here. The blocking queue is replaced by a synchronous queue which only has space for one task. A new request is stored in the queue while it searches for any available thread. If no thread is available then it’ll create a new thread and add it to the pool. It also has the ability to kill threads which have been idle for more than 60 seconds.


```java
5. ExecutorService execService = Executors.newWorkStealingPool();
```
Creates a work-stealing thread pool using all available processors as its target parallelism level

Once the thread pool is created, we can perform the required action on them. Most used methods and their functionalities are as below:

- **execute(Runnable) :** Executes the given command at some time in the future. It does not return anything.
- **submit(Runnable) :** executes the passed task and returns a Future Object representing the executed task.This method accepts a Runnable task for execution and returns a Future object representing that task. Since a Runnable does not return a result, the returned Future's get method will return null upon successful completion.
- **submit(Callable) :** The Callable interface is similar to Runnable, but it can return a result and throw a checked exception. It is a generic interface with a single method call that returns a result of type V..
- **invokeAll(Collection<? extends Callable<T>> tasks):** Executes the given tasks, returning a list of Futures holding their status and results when all complete.
- **invokeAny(Collection<? extends Callable<T>> tasks):** Executes the given tasks, returning the result of one that has completed successfully (i.e., without throwing an exception), if any do.
- **awaitTermination(long timeout, TimeUnit unit):** Blocks until all tasks have completed execution after a shutdown request, or the timeout - occurs, or the current thread is interrupted, whichever happens first.
- **isShutdown():** Returns true if this executor has been shut down.
-** isTerminated():** Returns true if all tasks have been completed following shut down.
-** shutdown() :** Initiates the shutdown so that previously submitted tasks are executed, but no new task will be accepted. Invocation has no additional effect if already shut down.
- **shutdownNow() :** Attempts to stop all actively executing tasks, halts the processing of waiting tasks, and returns a list of the tasks that were awaiting execution.

### Example 1 Execute Method : Using ExecutorService to Execute a Runnable

```java

package com.vino.info.example.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Example1ExecuteMethod {
    public static void main(String[] args) {
        // Create an ExecutorService with a fixed thread pool of size 2
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Create an instance of the Runnable task
        MyRunnableTask task = new MyRunnableTask();

        // Submit the task to the executor for execution
        executorService.execute(task);

        // Properly shut down the executor
        executorService.shutdown();
    }
}

class MyRunnableTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Task is running in: " + Thread.currentThread().getName());
    }
}

```
### Example 2 Execute Method : This Example that includes creating multiple Runnable tasks, submitting them to an ExecutorService, and demonstrating how the tasks are executed concurrently.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example2ExecuteMethod {
    public static void main(String[] args) {
        // Create an ExecutorService with a fixed thread pool of size 3
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit multiple tasks to the executor
        for (int i = 1; i <= 5; i++) {
            executorService.execute(new MyRunnableTask(i));
        }

        // Properly shut down the executor
        executorService.shutdown();
    }
}

public class MyRunnableTask implements Runnable {
    private final int taskId;

    public MyRunnableTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Task " + taskId + " is running in: " + Thread.currentThread().getName());
        try {
            // Simulate some work with a sleep
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Task " + taskId + " was interrupted.");
        }
        System.out.println("Task " + taskId + " is completed in: " + Thread.currentThread().getName());
    }
}

```
The output will demonstrate how the tasks are run concurrently by the thread pool. You will see that up to 3 tasks are running at the same time since the pool size is 3.

### OUTPUT

```java
Task 1 is running in: pool-1-thread-1
Task 2 is running in: pool-1-thread-2
Task 3 is running in: pool-1-thread-3
Task 1 is completed in: pool-1-thread-1
Task 4 is running in: pool-1-thread-1
Task 2 is completed in: pool-1-thread-2
Task 5 is running in: pool-1-thread-2
Task 3 is completed in: pool-1-thread-3
Task 4 is completed in: pool-1-thread-1
Task 5 is completed in: pool-1-thread-2
```
In this output, you can observe how tasks 1, 2, and 3 start simultaneously, followed by tasks 4 and 5 after the first three are completed. This demonstrates the concurrent execution managed by the ExecutorService.

### Example 3 :  ExecutorService to submit a Runnable task in Java.[submit(Runnable)]

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {

    public static void main(String[] args) {
        // Create a thread pool with a fixed number of threads
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // Create a Runnable task
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    // Simulate some work with Thread.sleep
                    System.out.println("Task is running on thread: " + Thread.currentThread().getName());
                    Thread.sleep(2000);  // Sleep for 2 seconds
                    System.out.println("Task completed on thread: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // Submit the Runnable task to the ExecutorService
        executorService.submit(task);

        // Shutdown the ExecutorService
        executorService.shutdown();
    }
}

```
### Output:

```java
Task is running on thread: pool-1-thread-1
Task completed on thread: pool-1-thread-1
```

### Example 4 :  ExecutorService to submit a Runnable mutiple task in Java.[submit(Runnable)]

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {

    public static void main(String[] args) {
        // Create a thread pool with a fixed number of threads
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // Create and submit multiple Runnable tasks
        for (int i = 1; i <= 10; i++) {
            int taskId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    try {
                        // Simulate some work with Thread.sleep
                        System.out.println("Task " + taskId + " is running on thread: " + Thread.currentThread().getName());
                        Thread.sleep(2000);  // Sleep for 2 seconds
                        System.out.println("Task " + taskId + " completed on thread: " + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.submit(task);
        }

        // Shutdown the ExecutorService
        executorService.shutdown();
    }
}

```
### OUTPUT
```java
Task 1 is running on thread: pool-1-thread-1
Task 2 is running on thread: pool-1-thread-2
Task 3 is running on thread: pool-1-thread-3
Task 4 is running on thread: pool-1-thread-4
Task 5 is running on thread: pool-1-thread-5
Task 1 completed on thread: pool-1-thread-1
Task 6 is running on thread: pool-1-thread-1
Task 2 completed on thread: pool-1-thread-2
Task 7 is running on thread: pool-1-thread-2
Task 3 completed on thread: pool-1-thread-3
Task 8 is running on thread: pool-1-thread-3
Task 4 completed on thread: pool-1-thread-4
Task 9 is running on thread: pool-1-thread-4
Task 5 completed on thread: pool-1-thread-5
Task 10 is running on thread: pool-1-thread-5
...
```

### Example 5 :  An Example where we have multiple student tasks that represent students doing some work. Each student task will print its name, simulate doing homework by sleeping for a random amount of time, and then print a completion message.[submit(Runnable)]

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class StudentExample {

    public static void main(String[] args) {
        // Create a thread pool with a fixed number of threads
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // Create and submit multiple student tasks
        String[] studentNames = {"Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Hannah", "Ivan", "Jack"};

        for (String studentName : studentNames) {
            Runnable studentTask = new Runnable() {
                @Override
                public void run() {
                    try {
                        // Simulate doing homework with Thread.sleep
                        System.out.println(studentName + " is doing homework on thread: " + Thread.currentThread().getName());
                        int sleepTime = ThreadLocalRandom.current().nextInt(1000, 3000);  // Sleep for a random time between 1 and 3 seconds
                        Thread.sleep(sleepTime);
                        System.out.println(studentName + " completed homework on thread: " + Thread.currentThread().getName() + " (slept for " + sleepTime + " ms)");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.submit(studentTask);
        }

        // Shutdown the ExecutorService
        executorService.shutdown();
    }
}

```
### OUTPUT

```java
Alice is doing homework on thread: pool-1-thread-1
Bob is doing homework on thread: pool-1-thread-2
Charlie is doing homework on thread: pool-1-thread-3
Diana is doing homework on thread: pool-1-thread-4
Eve is doing homework on thread: pool-1-thread-5
Alice completed homework on thread: pool-1-thread-1 (slept for 1723 ms)
Bob completed homework on thread: pool-1-thread-2 (slept for 2556 ms)
Charlie completed homework on thread: pool-1-thread-3 (slept for 1345 ms)
Frank is doing homework on thread: pool-1-thread-1
Diana completed homework on thread: pool-1-thread-4 (slept for 1984 ms)
Grace is doing homework on thread: pool-1-thread-2
Eve completed homework on thread: pool-1-thread-5 (slept for 2678 ms)
Hannah is doing homework on thread: pool-1-thread-3
...

```









