package base.reflection反射;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Reflection_Demo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchFieldException {
//        test01();

        test02();
    }

    private static void test02() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        //获取 TargetObject 类的 Class 对象并且创建 TargetObject 类实例
        Class<?> targetClass = Class.forName("com.yzm.base.reflection反射.TargetObject");
        TargetObject targetObject = (TargetObject) targetClass.newInstance();

        //获取 TargetObject 类中定义的所有方法
        Method[] methods = targetClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        //获取指定方法并调用
        Method publicMethod = targetClass.getDeclaredMethod("publicMethod", String.class);
        publicMethod.invoke(targetObject, "hello world");

        //调用 private 方法
        Method privateMethod = targetClass.getDeclaredMethod("privateMethod");
        //为了调用private方法我们取消安全检查
        privateMethod.setAccessible(true);
        privateMethod.invoke(targetObject);

        //获取指定参数并对参数进行修改
        Field field = targetClass.getDeclaredField("value");
        //为了对类中的参数进行修改我们取消安全检查
        field.setAccessible(true);
        field.set(targetObject, "yzm");
        //查看修改后的属性值
        privateMethod.invoke(targetObject);
    }

    private static void test01() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("list = " + list);

        //创建Class对象的常用方式
//        Class<? extends List> aClass = list.getClass();
//        Class<List> aClass = List.class;
        Class<?> aClass = Class.forName("java.util.List");

        //获取指定方法对象，指定方法参数类型
        Method add = aClass.getMethod("add", Object.class);
        add.setAccessible(true);
        add.invoke(list, "a");
        System.out.println("list = " + list);
    }
}


class TargetObject {
    private String value;

    public TargetObject() {
        value = "admin";
    }

    public void publicMethod(String s) {
        System.out.println("I love " + s);
    }

    private void privateMethod() {
        System.out.println("value is " + value);
    }
}
