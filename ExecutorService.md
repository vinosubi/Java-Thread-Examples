# ExecutorService

`ExecutorService:`

**Whats exactly concurrency ?**
Concurrency is the ability of a system to manage multiple tasks simultaneously. It is essential for improving the performance and responsiveness of the system.

![image](https://github.com/vinosubi/Java-Thread-Examples/assets/133937082/ac0dcb21-41f1-446c-8282-5298770a66b8)

An ExecutorService is a utility in Java that provides a way to execute tasks concurrently.
It manages a pool of worker threads, and allows you to submit tasks for execution.
The ExecutorService handles creation, management, and reusability of threads, making it easier to handle concurrent tasks in multithreaded applications.

![image](https://github.com/vinosubi/Java-Thread-Examples/assets/133937082/b57765f9-cdbf-4017-9f80-5433ddf6a8ea)

Starting from Java 5, JDK provides API ExecutorService that simplifies running tasks in asynchronous mode. It provides a pool of threads and an API to assign tasks to it.

ExecutorService is an interface provided by JDK. Its object can be obtained using class Executors. There are 5 types of thread pools we can create

```java
ExecutorService execService = Executors.newSingleThreadExecutor(10); 
// Creates an Executor that uses a single thread

ExecutorService execService = Executors.newFixedThreadPool(10); 
// Creates a thread pool that reuses a fixed number of threads

ExecutorService execService = Executors.newScheduledThreadPool(10); 
// Creates a thread pool that can schedule commands to run after a given delay, or to execute periodically.

ExecutorService execService = Executors.newCachedThreadPool(); 
// Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available.

ExecutorService execService = Executors.newWorkStealingPool(); 
// Creates a work-stealing thread pool using all available processors as its target parallelism level

```




