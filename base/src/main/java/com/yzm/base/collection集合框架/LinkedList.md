# 
```text

LinkedList 继承了 AbstractSequentialList 类。
LinkedList 实现了 List 接口，可进行列表的相关操作。
LinkedList 实现了 Deque 接口，可作为双端队列使用。
LinkedList 实现了 Cloneable 接口，可实现克隆。
LinkedList 实现了 java.io.Serializable 接口，即可支持序列化，能通过序列化去传输。
```
# 主要属性
```java
public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, java.io.Serializable {
    
    // 链表元素个数
    transient int size = 0;
    
    // 链接首节点
    transient Node<E> first;

    // 链接尾节点
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
    
}
```
# 添加元素(查看源码时，至少观察添加2个元素以上)
```java
public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, java.io.Serializable {

    // 将指定元素追加到此列表的末尾。这个方法等价于addLast。
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    // 节点添加到链表尾部
    void linkLast(E e) {
        // 添加新节点前的链表尾节点
        final Node<E> l = last;
        // 新建节点作为新的尾节点
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        // 如果是首次添加节点，链表的last、first节点都是null
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }
}
```
# 删除元素
```java
public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, java.io.Serializable {

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
        // assert f == first && f != null;
        // 拿到首节点的元素以及下一个节点对象，然后置空
        // 首节点的上一个节点是null不需要处理
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        
        // 经移除后，首节点的下一个节点作为新的首节点
        first = next;
        if (next == null)
            // next为空，则说明原链表只有一个节点
            last = null;
        else
            // 否则将上一个节点置空，因为首节点的上一个节点必须为null
            next.prev = null;
        size--;
        modCount++;
        return element;
    }
}
```