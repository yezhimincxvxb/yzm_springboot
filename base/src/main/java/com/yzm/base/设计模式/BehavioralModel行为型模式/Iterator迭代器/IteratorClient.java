package com.yzm.base.设计模式.BehavioralModel行为型模式.Iterator迭代器;

import java.util.ArrayList;
import java.util.List;

/**
 * 迭代器模式（Iterator Pattern）是 Java 和 .Net 编程环境中非常常用的设计模式。这种模式用于顺序访问集合对象的元素，不需要知道集合对象的底层表示。
 * 迭代器模式属于行为型模式。
 * <p>
 * 意图：提供一种方法顺序访问一个聚合对象中各个元素, 而又无须暴露该对象的内部表示。
 * 主要解决：不同的方式来遍历整个整合对象。
 * 何时使用：遍历一个聚合对象。
 * 如何解决：把在元素之间游走的责任交给迭代器，而不是聚合对象。
 * 关键代码：定义接口：hasNext, next。
 * 应用实例：JAVA 中的 iterator。
 * 优点： 1、它支持以不同的方式遍历一个聚合对象。 2、迭代器简化了聚合类。 3、在同一个聚合上可以有多个遍历。 4、在迭代器模式中，增加新的聚合类和迭代器类都很方便，无须修改原有代码。
 * 缺点：由于迭代器模式将存储数据和遍历数据的职责分离，增加新的聚合类需要对应增加新的迭代器类，类的个数成对增加，这在一定程度上增加了系统的复杂性。
 * 使用场景： 1、访问一个聚合对象的内容而无须暴露它的内部表示。 2、需要为聚合对象提供多种遍历方式。 3、为遍历不同的聚合结构提供一个统一的接口。
 * 注意事项：迭代器模式就是分离了集合对象的遍历行为，抽象出一个迭代器类来负责，这样既可以做到不暴露集合的内部结构，又可让外部代码透明地访问集合内部的数据。
 */
public class IteratorClient {
    public static void main(String[] args) {
        Container container = new ConcreteContainer();
        container.add("AAA");
        container.add("DDD");
        container.add("CCC");
        container.add("BBB");
        container.add("EEE");

        Iterator iterator = container.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}

//容器角色
interface Container {
    void add(Object obj);

    void remove(Object obj);

    Iterator iterator();
}

//迭代器角色
interface Iterator {
    Object first();

    boolean hasNext();

    Object next();
}

//具体容器角色（Concrete Container）
class ConcreteContainer implements Container {
    private final List<Object> list;

    public ConcreteContainer() {
        this.list = new ArrayList<>();
    }

    public ConcreteContainer(List<Object> list) {
        this.list = list;
    }

    @Override
    public void add(Object obj) {
        list.add(obj);
    }

    @Override
    public void remove(Object obj) {
        list.remove(obj);
    }

    @Override
    public Iterator iterator() {
        return new ConcreteIterator();
    }

    //具体迭代器角色（Concrete Iterator）
    private class ConcreteIterator implements Iterator {
        private int position = 0;

        @Override
        public Object first() {
            position = 0;
            return list.get(position);
        }

        @Override
        public boolean hasNext() {
            return position < list.size();
        }

        @Override
        public Object next() {
            Object ret = null;
            if (hasNext()) {
                ret = list.get(position);
            }
            position++;
            return ret;
        }
    }
}