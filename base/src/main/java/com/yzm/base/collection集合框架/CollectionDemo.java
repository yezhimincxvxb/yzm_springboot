package com.yzm.base.collection集合框架;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionDemo {
    public static void main(String[] args) {
        Collection collection = new ArrayList();

        collection.add(11);
        collection.add("22");
        collection.add(new Object());
        collection.add(null);

        System.out.println("collection = " + collection);

        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println("next = " + next);
        }

        collection.remove("22");

        for (Object o : collection) {
            System.out.println("o = " + o);
        }
    }


}
