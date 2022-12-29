### 线程池 Executor
```text
通过重用线程池中的线程，来减少每个线程创建和销毁的性能开销。
对线程进行一些维护和管理，比如定时开始，周期执行，并发数控制等等。
```
### 线程池类型 Executors 线程池工具类创建
```text
ExecutorService newFixedThreadPool() : 创建固定大小的线程池
ExecutorService newCachedThreadPool() : 缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。
ExecutorService newSingleThreadExecutor() : 创建单个线程池。 线程池中只有一个线程
ScheduledExecutorService newScheduledThreadPool() : 创建固定大小的线程，可以延迟或定时的执行任务
ScheduledExecutorService newSingleThreadScheduledExecutor() : 创建单个可以延迟或定时的执行任务线程池

```

### ThreadPoolExecutor
```text
ThreadPoolExecutor(
    int corePoolSize,
    int maximumPoolSize,
    long keepAliveTime,
    TimeUnit unit,
    BlockingQueue<Runnable> workQueue,
    ThreadFactory threadFactory,
    RejectedExecutionHandler handler
)
参数说明
corePoolSize：核心线程数，默认情况下核心线程会一直存活，即使处于闲置状态也不会受存keepAliveTime限制。除非将allowCoreThreadTimeOut设置为true。
maximumPoolSize：线程池所能容纳的最大线程数。超过这个数的线程将被阻塞。当任务队列为没有设置大小的LinkedBlockingDeque时，这个值无效。
keepAliveTime：非核心线程的闲置超时时间，超过这个时间就会被回收。
unit：指定keepAliveTime的单位，如TimeUnit.SECONDS。当将allowCoreThreadTimeOut设置为true时对corePoolSize生效。
workQueue：线程池中的任务队列.常用的有三种队列，SynchronousQueue,LinkedBlockingQueue,ArrayBlockingQueue。
threadFactory：线程工厂，提供创建新线程的功能。ThreadFactory是一个接口，只有一个方法
public interface ThreadFactory {
Thread newThread(Runnable r);
}
handler：RejectedExecutionHandler也是一个接口，只有一个方法
public interface RejectedExecutionHandler {
void rejectedExecution(Runnable var1, ThreadPoolExecutor var2);
}
当线程池中的资源已经全部使用，添加新线程被拒绝时，会调用RejectedExecutionHandler的rejectedExecution方法。
new ThreadPoolExecutor.DiscardPolicy()
// AbortPolicy:直接抛出java.util.concurrent.RejectedExecutionException异常
// CallerRunsPolicy:若已达到待处理队列长度，将由主线程直接处理请求
// DiscardOldestPolicy:抛弃旧的任务；会导致被丢弃的任务无法再次被执行
// DiscardPolicy:抛弃当前任务；会导致被丢弃的任务无法再次被执行

```

### 队列
```text
队列                 是否有界        是否缓冲        锁个数             并发性能

SynchronousQueue     无              无              1               线程少 (<20) ，Queue长度短 (<30) , 使用SynchronousQueue表现很稳定，
而且在20个线程之内不管Queue长短，SynchronousQueue性能表现是最好的，
SynchronousQueue跟Queue长短没有关系）

ArrayBlockingQueue   有              有            1                一般不用，并且在插入和删除元素时会产生额外的对象(跟LinkedBlockingQueue区别是必须指定大小)

LinkedBlockingQueue  无              有       2(生产者锁和消费者锁)  LinkedBlockingQueue性能表现远超ArrayBlockingQueue，不管线程多少，不管Queue长短，
LinkedBlockingQueue都胜过ArrayBlockingQueue，线程多（>20），Queue长度长（>30），
使用LinkedBlockingQueue(当LinkedBlockingQueue没指定大小是，最大线程数无效)

```
### 线程池规则
```text
如果当前运行的线程数小于corePoolSize，有空闲的核心线程，则用空闲的线程来执行任务，没有就创建新线程(核心线程)来执行任务。
如果当前运行的线程数大于或等于corePoolSize，那么就把任务加入等待队列。
如果等待队列已满，那么创建新线程(非核心线程)来执行该任务。
如果创建新线程时导致当前运行的线程数超过maximumPoolSize，就根据饱和策略来拒绝该任务

```
### ThreadPoolTaskExecutor 是对ThreadPoolExecutor的封装，并增强了功能，比如任务调度

### ScheduledThreadPoolExecutor 定时调度






































































