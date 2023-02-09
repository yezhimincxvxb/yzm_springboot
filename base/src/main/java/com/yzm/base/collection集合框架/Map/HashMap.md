# 主要源码
```java
public class HashMap<K,V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable {
    //----------------------------属性---------------------------
    // 默认初始容量-必须是2的幂。
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    // 最大容量，如果其中一个构造函数使用参数隐式指定了更高的值，则使用该容量。必须是2的幂<= 1<<30。
    static final int MAXIMUM_CAPACITY = 1 << 30;
    // 默认负载因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    // 树化阈值
    static final int TREEIFY_THRESHOLD = 8;
    // 取消树化阈值
    static final int UNTREEIFY_THRESHOLD = 6;
    // 树化的容器的最小容量。
    static final int MIN_TREEIFY_CAPACITY = 64;
    // 哈希节点
    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
    }
    // 节点数组
    transient Node<K,V>[] table;
    // entrySet
    transient Set<Map.Entry<K,V>> entrySet;
    // map中键值对数量
    transient int size;
    // map结构修改次数，用于迭代器快速失败
    transient int modCount;
    // 扩容阈值=(容量 * 负载因子)
    int threshold;
    // 哈希表的加载因子,通常情况=负载因子
    final float loadFactor;

    //----------------------------构造器---------------------------
    // 构造一个具有指定初始容量和负载因子的空HashMap。
    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }
    // 构造一个具有指定初始容量和默认负载因子(0.75)的空HashMap。
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }
    // 构造一个具有默认初始容量(16)和默认负载因子(0.75)的空HashMap。
    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    //----------------------------添加元素---------------------------
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }
    // 计算hash值
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        // 辅助变量：p链表节点，n数组长度，i元素命中的索引位置
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        // 步骤1、判断数组已初始化，没有则扩容
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        // 步骤2、hash值与数组大小取余，定位索引位置，若索引位置为空，新建节点
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        // 步骤3、计算后的索引位置已有节点，细分3种情况
        else {
            // 辅助变量：e=健值的定位节点
            Node<K,V> e; K k;
            // 步骤3.1、比较节点p(首节点)的hash值、键值、equals()方法
            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            // 步骤3.2、节点p(首节点)是树节点TreeNode，执行树插入
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            // 步骤3.3、遍历链表
            else {
                for (int binCount = 0; ; ++binCount) {
                    // 节点p(既是首节点也是尾节点)或已遍历到链表的尾节点，新建节点作为新的尾节点
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        // 新建链表节点后，判断是否需要树化，树化条件链表长度>8，数组容量>64
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    // 遍历节点的hash值、键值、equals()方法，找到健值对应的定位节点e
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    // 辅助变量循环赋值，已达到遍历后移
                    p = e;
                }
            }
            // e为null的情况是键值刚好添加链表尾部，不为null则说明需要覆盖value值
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                // 该方法由HashMap定义，对其子类LinkedHashMap有效
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        // 添加元素后，判断元素个数是否大于阈值，初次阈值12
        if (++size > threshold)
            resize();
        // 该方法由HashMap定义，对其子类LinkedHashMap有效
        afterNodeInsertion(evict);
        return null;
    }
    // 扩容
    final Node<K,V>[] resize() {
        Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold; // 默认为0，若指定初始容量则不为0
        int newCap, newThr = 0;
        // 非初次扩容
        if (oldCap > 0) {
            // 容量已达到最大值，不进行扩容
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            // 扩容大小，新的容量以及新的阈值为原来的2倍
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
        // 指定初始容量的初次扩容
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        // 空参构造初次扩容
        else {               // zero initial threshold signifies using defaults
            newCap = DEFAULT_INITIAL_CAPACITY; // 默认初始容量16
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY); // 阈值=默认负载因子0.75 * 默认初始容量16 = 12 
        }
        // 指定初始容量的初次扩容的情况，newThr为0
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor; // loadFactor指定负载因子
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ? (int)ft : Integer.MAX_VALUE);
        }
        
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap]; // 创建节点数组
        table = newTab;
        // 遍历旧数组将节点重新添加到新数组中
        if (oldTab != null) {
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                if ((e = oldTab[j]) != null) { // 索引位置有节点
                    oldTab[j] = null;
                    // 该索引对应的链表只有一个节点，根据原hash值重新计算新数组的索引位置
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    // e节点是树节点
                    else if (e instanceof TreeNode)
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    // 索引位置已形成链表
                    else { // preserve order
                        // 记录索引不变的节点
                        Node<K,V> loHead = null, loTail = null;
                        // 记录索引会变的节点
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            // 筛选等于0，索引位置保持不变
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            // 筛选不等于0，新索引位置=旧索引位置+旧容量大小
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null); // 结束条件遍历到链表尾部节点
                        if (loTail != null) {
                            loTail.next = null;
                            // 新旧索引位置保持不变
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            // 新索引位置=旧索引位置+旧容量大小
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }
    
    //---------------------获取元素---------------------
    // 计算key的hash值
    public V get(Object key) {
        Node<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }

    final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        // 数组已初始化并且根据hash值对数组长度取余得到索引位置不为空
        if ((tab = table) != null && (n = tab.length) > 0 && (first = tab[(n - 1) & hash]) != null) {
            // 比较索引位置节点的hash值、键值和equals，若一致则返回该节点
            if (first.hash == hash && ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            // 不一致，判断首节点是否有下一个节点
            if ((e = first.next) != null) {
                // 节点可能是树节点，执行树查找
                if (first instanceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                // 遍历链表，判断Key是否存在
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }
}
```
# HashMap的实现原理
```text

```
# put添加流程
```text
1、首先获取key的hashCode并经过2次位运算得到hash值
2、然后判断节点数组是否已初始化，没有则进行扩容
3、数组已初始化，根据hash值对数组长度取余，定位索引位置，若索引位置为空则直接新建节点
4、索引位置不为空，比较添加元素的hash值、键值以及equals方法，来判断Key是否存在，若存在则定位节点并之后覆盖value值
5、若不存在，则判断索引位置节点是否是树节点，若是则执行树插入操作
6、若不是树节点，则遍历链表，判断Key是否存在，存在定位后结束遍历之后覆盖value；
7、Key不存在则遍历到尾部新增节点，新增节点后先判断链表长度是的大于8，再判断容量是否大于64，都大于树化，只大于8则扩容
8、最后比较集合size是否大于阈值threshold，大于则扩容 
```
# 负载因子为什么是0.75
# 扩容机制
```text

```
# HashMap时一般使用什么类型的元素作为Key
```text
String或者Integer这样的类。
这些类是final类，不可变类，是线程安全的；可以缓存hash值，避免重复计算；覆写了hashCode()以及equals()方法。
```
# 为什么HashMap的长度必须是2的n次幂？
```text
存储元素时，需要计算hash值然后对数组长度取余，得到存储位置。对于计算机来说取余运算并不高效。
当数组长度为2的n次幂时，取余运算可以被按位与运算代替
```
# HashMap 为什么在获取 hash 值时要进行位运算
```text
hash值 = key.hashCode()) ^ (h >>> 16)
无符号右移16位,是为了拿到hashCode的高16位
HashMap 数组长度一般不会超过2的16次幂,那么高16位在大多数情况是用不到的
拿到高16位然后按位异或，是进一步降低hash碰撞的概率，使得hash值更加散列,提升性能
```