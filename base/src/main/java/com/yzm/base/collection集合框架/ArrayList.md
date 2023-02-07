# ArrayList
```text

```
# 源码分析
```java
public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
    //--------------------属性------------------------
    //  默认初始容量
    private static final int DEFAULT_CAPACITY = 10;
    // 用于空实例的共享空数组实例。
    private static final Object[] EMPTY_ELEMENTDATA = {};
    // 用于默认大小的空实例的共享空数组实例。我们将它与 EMPTY_ELEMENTDATA 区别开来，以了解添加第一个元素时要膨胀多少。
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    // 数组缓冲区，数组列表的元素被存储在其中。数组列表的容量就是这个数组缓冲区的长度。
    // 当添加第一个元素时，任何带有elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA 的空数组列表将被扩展为DEFAULT_CAPACITY。
    transient Object[] elementData; // non-private to simplify nested class access
    // 数组列表的大小(包含元素的数量)。
    private int size;

    //--------------------构造器------------------------
    // 空参构造，初始容量为10
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    // 构造具有指定初始容量的空列表。 参数: initialCapacity -列表的初始容量 抛出: 如果指定的初始容量为负数
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    //--------------------添加元素------------------------
    // 添加元素
    public boolean add(E e) {
        // 第一步：确认是否需要扩容
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        // 第二步，执行赋值
        elementData[size++] = e;
        return true;
    }

    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    // 确认数组所需的最小容量，若是空实例为10
    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    private void ensureExplicitCapacity(int minCapacity) {
        // 记录集合被修改次数
        modCount++;

        // 添加元素后所需的最小容量要比当前数组长度还大，才进行扩容
        // 10 > 0 大
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    private void grow(int minCapacity) {
        // 扩容机制：数组长度 + 数组长度/2
        // 扩容：(0 < 10)10、15、22、33、49...
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        // 经扩容后的大小比所需的最小容量还小，选较大值
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;

        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
}
```
# ArrayList特性
```text
继承AbstractList抽象类，==实现List接口==，还实现了RandomAccess==快速随机访问==以及Cloneable, java.io.Serializable克隆和序列化
底层数据结构是==数组==，连续内存空间，使用==for循环遍历效率最高==，尾部添加和删除元素时效率也很高，==非线程安全==
数组容量==动态自增==，当容量大于当前数组容量时进行扩容，==容量大小增加50%==，每次扩容都会开辟新空间，并且进行==新老数组的复制重排==

ArrayList常用API
增：效率add(E e) > add(int index,E element)
删：效率remove(int index) > remove(E e)
改：set(int index,E element)
查：get(int index)   
```
# ArrayList 属性elementData数组使用了transient修饰，作用是什么？
```text
使用transient关键词修饰属性，表示不能被外部方法序列化
ArrayList 的elementData数组是动态扩容的，并非所有被分配的内存空间都存储了数据，如果采用外部序列化法实现序列化，那整个elementData都会被序列化
ArrayList为了避免这些没有存储数据的内存空间被序列化，内部提供了两个私有方法 writeObject 以及 readObject 来自我完成序列化与反序列化，从而在序列化与反序列化数组时节省了空间和时间。
```
# ArrayList如何添加元素效率最高？
```text
ArrayList 新增元素的方法常用的有两种，一种是：直接添加元素；另外一种是：指定索引位置添加
使用ArrayList的add(E e)方法直接添加元素，默认将元素添加到数组尾部，在没有发生扩容的情况下，不会有数组的复制重排过程，效率最高
使用add(int index,E element)添加元素到指定位置，index取值范围[0,size-1],添加元素时，会导致在该位置后的所有元素都需要进行复制排列，效率较低
```
# ArrayList为什么是非线程安全的？ 
```text
因为ArrayList添加元素时，主要会进行这两步操作，一是：判断数组是否需要扩容；，二是：在数组对应位置赋值。这2步操作在多线程访问时都存在安全隐患
第1个隐患是，当集合size=9(本次添加完元素后下一次就会触发扩容的情况)时，多个线程AB读取到相同的size值，对size+1进行判断容量时，刚好满足容量大小，都不进行扩容。
    线程A赋值成功并对size+1，此时线程B再进行赋值就会抛出数组下标越界异常
第2个隐患是，当集合size=0(或添加多个元素后都不会触发扩容的情况)时，在数组对应位置赋值elementData [size++] = e; 实际上会被分解成两步进行，先赋值 elementData [size] = e;再进行size=size+1; 
    可能第线程A刚赋值完，还没进行size+1，线程B就对同一位置进行赋值，导致前面线程添加的元素值被覆盖。
```
# ArrayList在foreach循环或迭代器遍历中，调用自身的remove(E e)方法删除元素，会导致什么问题？
```text
会抛并发修改异常ConcurrentModificationException，原因是：集合的修改次数modCount和迭代器的期望修改次数expectedModCount不一致
foreach循环相当于迭代器。在迭代器遍历中，使用集合自身的remove()方法删除元素，内部会进行modCount++，但并不会进行迭代器的expectedModCount++，
因此导致进入下一趟遍历调用迭代器的next()方法中，内部比对两者不一致抛出并发修改异常
```
# 迭代器 Iterator、ListIterator
```text
Iterator 可以遍历 Set 和 List 集合，而 ListIterator 只能遍历 List。
Iterator 只能单向遍历，而 ListIterator 可以双向遍历（向前/后遍历）。
ListIterator 继承 Iterator 接口，然后添加了一些额外的功能，比如添加一个元素、替换一个元素、获取前面或后面元素的索引位置。
```
# fail-fast(快速失败)、fail-safe(安全失败)
```text
fail-fast不允许遍历的同时对容器中的数据进行修改
fail-safe允许在遍历的过程中对容器中的数据进行修改，原理是读写分离
java.util下的所有集合类都是 fail-fast 机制，ArrayList、HashMap
而 java.util.concurrent 包下的所有集合类都是 fail-safe机制。CopyOnWriteArrayList、ConcurrentHashMap
```