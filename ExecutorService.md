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






