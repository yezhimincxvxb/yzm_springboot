package com.yzm.base.collection集合框架;

import java.util.Vector;

public class VectorDemo {
    public static void main(String[] args) {
        Vector vector = new Vector();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        System.out.println(vector);

        vector.remove(1);
        System.out.println(vector);
    }
}
