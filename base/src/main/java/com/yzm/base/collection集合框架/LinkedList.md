# 主要源码
```java
public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, java.io.Serializable {
    //--------------------属性------------------------
    // 元素个数
    transient int size = 0;
    // 首节点
    transient Node<E> first;
    // 尾节点
    transient Node<E> last;

    // 节点
    private static class Node<E> {
        // 真实的元素对象
        E item;
        // 下一个节点
        Node<E> next;
        // 上一个节点
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    //--------------------构造器------------------------
    public LinkedList() {
    }
    
    //--------------------添加元素------------------------
    // 将指定元素追加到此列表的末尾。这个方法等价于addLast。
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    // 节点添加到链表尾部
    void linkLast(E e) {
        // 拿到链表的尾节点
        final Node<E> l = last;
        // 新建节点作为新的尾节点
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;

        if (l == null)
            // l==null说明添加前该链表是空的，添加的元素为第一个元素，既是首节点也是尾节点
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }

    //--------------------移除元素------------------------
    public E remove() {
        return removeFirst();
    }

    public E removeFirst() {
        // 空链表移除元素会报异常
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    private E unlinkFirst(Node<E> f) {
        // 拿到首节点的元素以及下一个节点对象，然后置空
        // 首节点的上一个节点是null不需要处理
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC

        // 经移除后，原首节点的下一个节点作为新的首节点
        first = next;
        if (next == null)
            // next为空，则说明原链表只有一个节点
            last = null;
        else
            // 否则将上一个节点置空，因为首节点是没有上一个节点的
            next.prev = null;

        size--;
        modCount++;
        return element;
    }
    
}
```
# LinkedList为什么不能实现RandomAccess接口
```text
因为LinkedList底层数据结构是链表，内存地址不连续，只能通过指针来定位，不支持随机快速访问，所以不能实现 RandomAccess 接口
```
# LinkedList几个重要属性各自的含义
```text
LinkedList底层主要属性有size集合大小（链表长度）、first链表头部节点、last链表尾部节点，并且也都使用transient修饰，
表示不能外部序列化与反序列化，内部自己实现了序列化与反序列化。
```
# LinkedList使用哪种方式遍历效率最高
```text
LinkedList底层数据结构是双向链表的，使用foreach循环或iterator迭代器遍历效率最高，通过迭代器的hasNext()、next()快速遍历元素
需要注意的是尽量避免使用for循环遍历LinkedList，因为每一次循环，都会使用二分查找先找到指定位置的节点，再通过该节点新建节点以及变换指针，效率极其低下。
```
# LinkedList默认位置添加元素和指定位置添加元素分别怎么实现，哪种性能更高
```text
LinkedList默认位置添加元素通过add(E e)方法实现，默认位置是添加元素到尾部，时间复杂度是O(1)，效率最高。
LinkedList指定位置添加元素通过addLast(E e)、addFirst(E e)方法添加元素到尾部和头部，add(E e)方法默认位置是添加元素到尾部，时间复杂度是O(1)，效率最高。
LinkedList还有一种指定位置添加元素通过add(int index, E element)方法实现，这种方式添加元素前，需要先通过二分查找找到指定位置的元素，再通过该元素进行==新节点创建以及指针变换==，综合时间复杂度是O(logN)，如果添加元素的位置刚好在中间，二分查找发挥的作用最小，效率比较低~
```
# ArrayList初始化容量大小足够的情况下，相比于LinkedList在头部、中间、尾部添加的效率如何
```text
头部：
ArrayList 底层结构是==数组==，数组是一块连续的内存空间，添加元素到数组头部时，需要对头部以后的==所有数据进行复制重排，效率最低，时间复杂度为O(n)==
LinkedList 底层结构是==链表==，通过addFirst(E e)方法添加元素到头部时，效率也最高，时间复杂度为O(1)-常数复杂度，不过多了新节点创建和指针变换的过程
中间：
ArrayList 添加元素到数组中间，==往后的所有数据需要都进行复制重排，时间复杂度为O(n)==；
LinkedList 添加元素到中间，==二分法查找发挥的作用最低==，不论从前往后，还是从后往前，链表被循环遍历的次数都是最多的，效率最低，综合==时间复杂度为O(n)==
结尾：
ArrayList 添加元素尾部，不需要进行复制重排数组数据，==效率最高==，时间复杂度为O(1)
LinkedList 添加元素到尾部，==不需要查找元素，效率也是最高的==，但是==多了==新节点对象创建以及==变换指针指向对象的过程==，==效率比ArrayList低一些==，时间复杂度为O(1)
```
# Arraylist 与 LinkedList 区别?
```text
是否保证线程安全： ArrayList 和 LinkedList 都是不同步的，也就是不保证线程安全；
底层数据结构： Arraylist 底层使用的是 Object 数组；LinkedList 底层使用的是 双向链表 数据结构
插入和删除是否受元素位置的影响： 
    ① ArrayList 采用数组存储，如果是尾插入(如：add(E e))时间复杂度就是 O(1)。
       但如果是指定位置i插入(如：add(int index, E element))那么时间复杂度就为 O(n-i)。只是因为i位置之后的元素需要复制重排
    ② LinkedList 采用链表存储，所以对于add(E e)方法的插入，删除元素时间复杂度不受元素位置的影响，近似 O(1)，
       但如果是要在指定位置i插入和删除元素的话(add(int index, E element) 时间复杂度近似为o(n))因为需要先移动到指定位置再插入。
是否支持快速随机访问： LinkedList 不支持高效的随机元素访问，而 ArrayList 支持。快速随机访问就是通过元素的序号快速获取元素对象(对应于get(int index)方法)。
内存空间占用： ArrayList 的空间浪费主要体现在在 list 列表的结尾会预留一定的容量空间，而 LinkedList 的空间花费则体现在它的每一个元素都需要消耗比 ArrayList 更多的空间（因为要存放直接后继和直接前驱以及数据）。
```