# ArrayList
```text
ArrayList 的底层是数组队列，相当于动态数组。与 Java 中的数组相比，它的容量能动态增长。
```
# 主要属性
```java
public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
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
}
```
# 构造器
```java
public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
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

    // 按照指定集合的迭代器返回元素的顺序，构造一个包含指定集合元素的列表。 
    // 参数: C -其元素将被放入此列表中的集合 抛出: NullPointerException -如果指定的集合为空
    public ArrayList(Collection<? extends E> c) {
        Object[] a = c.toArray();
        if ((size = a.length) != 0) {
            if (c.getClass() == ArrayList.class) {
                elementData = a;
            } else {
                elementData = Arrays.copyOf(a, size, Object[].class);
            }
        } else {
            // replace with empty array.
            elementData = EMPTY_ELEMENTDATA;
        }
    }
}
```
# 添加元素
```java
public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
    // 空参构造器
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

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

        // 所需的最小容量比当前数组长度还大，则进行扩容
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    private void grow(int minCapacity) {
        // 扩容机制：第一次为10
        // 之后按照原数组长度的1.5倍
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        // 扩容后的大小比所需的最小容量还小，选所需的最小容量
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
继承AbstractList抽象类，==实现List接口==，还实现了RandomAccess==快速随机访问==以及Cloneable克隆, 和java.io.Serializable序列化
底层数据结构是==数组==，连续内存空间，使用==for循环遍历效率最高==，尾部添加和删除元素时效率也很高，==非线程安全==
数组容量==动态自增==，当容量大于当前数组容量时进行扩容，==容量大小增加50%==，每次扩容都会开辟新空间，并且进行==新老数组的复制重排==
```


# Arraylist 和 Vector 的区别?
```text
ArrayList 是 List 的主要实现类，底层使用 Object[ ]存储，适用于频繁的查找工作，线程不安全 ；初始容量大小10，每次扩容1.5倍
Vector 是 List 的古老实现类，底层使用 Object[ ]存储，线程安全的；初始容量大小10，每次扩容2倍
```


# Arraylist 与 LinkedList 区别?
```text
是否保证线程安全： ArrayList 和 LinkedList 都是不同步的，也就是不保证线程安全；
底层数据结构： Arraylist 底层使用的是 Object 数组；LinkedList 底层使用的是 双向链表 数据结构（JDK1.6 之前为循环链表，JDK1.7 取消了循环。注意双向链表和双向循环链表的区别，下面有介绍到！）
插入和删除是否受元素位置的影响： 
    ① ArrayList 采用数组存储，如果是尾插入(如：add(E e)方法)时间复杂度就是 O(1)。
       但是如果是指定位置i插入(如：add(int index, E element)方法)那么时间复杂度就为 O(n-i)。只是因为i位置之后的元素都需要往后移一位
    ② LinkedList 采用链表存储，所以对于add(E e)方法的插入，删除元素时间复杂度不受元素位置的影响，近似 O(1)，
如果是要在指定位置i插入和删除元素的话(add(int index, E element) 时间复杂度近似为o(n))因为需要先移动到指定位置再插入。
是否支持快速随机访问： LinkedList 不支持高效的随机元素访问，而 ArrayList 支持。快速随机访问就是通过元素的序号快速获取元素对象(对应于get(int index)方法)。
内存空间占用： ArrayList 的空间浪费主要体现在在 list 列表的结尾会预留一定的容量空间，而 LinkedList 的空间花费则体现在它的每一个元素都需要消耗比 ArrayList 更多的空间（因为要存放直接后继和直接前驱以及数据）。
```
