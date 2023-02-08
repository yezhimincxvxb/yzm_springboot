# Arraylist 和 Vector 的区别?
```text
ArrayList 集合大小用size表示，Vector则是elementCount表示
ArrayList 只能指定初始容量大小Capacity，Vector可以指定初始容量大小也可以指定增长向量
ArrayList 空参构造是空实例此时size为0，Vector空参构造默认初始容量大小为10
ArrayList 初次扩容容量大小为10，之后每次扩容为原来的1.5倍；Vector每次扩容为默认是原来的2倍
ArrayList 非线程安全，Vector是线程安全的，通过synchronized保证
```
