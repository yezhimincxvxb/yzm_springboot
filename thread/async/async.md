### 概述
基于@Async标注的方法，称之为异步方法；这些方法将在执行的时候，将会在独立的线程中被执行，调用者无需等待它的完成，即可继续其他的操作

### 启用@Async
```text
@Configuration
@EnableAsync
public class AsyncConfig { ... }
```

### 配置类 实现AsyncConfigurer

### 三种使用方式
```text
无参无返回值
无参有返回值
有参有返回值
```

### @Async调用中的异常处理机制

### @Async调用中的事务处理机制
```text
在@Async标注的方法，同时也适用了@Transactional进行了标注；在其调用数据库操作之时，将无法产生事务管理的控制，原因就在于其是基于异步处理的操作。
那该如何给这些操作添加事务管理呢？可以将需要事务管理操作的方法放置到异步方法内部，在内部被调用的方法上添加@Transactional.
例如：
方法A，使用了@Async/@Transactional来标注，但是无法产生事务控制的目的。
方法B，使用了@Async来标注，  B中调用了C，C使用@Transactional做了标注，则可实现事务控制的目的。
```
