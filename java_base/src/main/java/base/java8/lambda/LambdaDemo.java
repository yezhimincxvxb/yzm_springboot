package base.java8.lambda;

import java.util.Arrays;
import java.util.function.*;

public class LambdaDemo {
    public static void main(String[] args) {
//        test01();
//        test02();
//        test03();
//        test04();
//        test05();
//        test06();
        test07();
    }

    /**
     * 构造器引用
     */
    public static void test07() {
        Supplier<Employee> sup = () -> new Employee();
        System.out.println(sup.get());
        Supplier<Employee> sup2 = Employee::new;
        System.out.println(sup2.get());

        Function<String, Employee> fun = (name) -> new Employee(name);
        Function<String, Employee> fun2 = Employee::new;
        System.out.println(fun2.apply("王大锤"));
    }

    /**
     * 方法引用
     */
    public static void test06() {
        /**
         *注意：
         * 1.lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致！
         * 2.若lambda参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
         */
        Consumer<Integer> con = (x) -> System.out.println(x);
        con.accept(100);

        // 方法引用-对象::实例方法
        Consumer<Integer> con2 = System.out::println;
        con2.accept(200);

        // 方法引用-类名::静态方法名
        BiFunction<Integer, Integer, Integer> biFun = (x, y) -> Integer.max(x, y);
        BiFunction<Integer, Integer, Integer> biFun2 = Integer::max;
        System.out.println("result = " + biFun2.apply(100, 200));

        // 方法引用-类名::实例方法名
        BiFunction<String, String, Boolean> fun1 = (str1, str2) -> str1.equals(str2);
        BiFunction<String, String, Boolean> fun2 = String::equals;
        System.out.println("result2 = " + fun2.apply("hello", "Hello"));
    }

    private static void test05() {
        boolean result = changeBoolean("hello", (str) -> str.length() > 5);
        System.out.println("result = " + result);
    }

    /**
     * Predicate<T> 断言型接口
     */
    public static boolean changeBoolean(String str, Predicate<String> pre) {
        return pre.test(str);
    }

    private static void test04() {
        Long result = changeNum(100L, (x) -> x + 200L);
        System.out.println(result);
    }

    /**
     * Function<T,R> 函数式接口
     */
    public static Long changeNum(Long num, Function<Long, Long> fun) {
        return fun.apply(num);
    }

    private static void test03() {
        String value = getValue(() -> "hello");
        System.out.println("value = " + value);
    }

    /**
     * Supplier<T> 供给型接口
     */
    public static String getValue(Supplier<String> sup) {
        return sup.get();
    }

    private static void test02() {
        printStr("hello Yzm", (str) -> System.out.println(str));
    }

    /**
     * Consumer<T> 消费型接口
     */
    public static void printStr(String str, Consumer<String> con) {
        con.accept(str);
    }

    /**
     * Java8 比较
     */
    private static void test01() {
        //Java8以前，如果想把某个接口的实现类作为参数传递给一个方法会怎么做？
        //要么创建一个类实现该接口，然后new出一个对象，在调用方法时传递进去，要么使用匿名类，可以精简一些代码。
        //以创建一个线程并打印一行日志为例，使用匿名函数写法如下：
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        }).start();

        //Java8
        new Thread(() -> System.out.println("Hello Again")).start();
    }
}

class Employee {
    String name = "Yzm";
    Integer age = 18;

    public Employee () {
    }

    public Employee (String name) {
        this.name = name;
    }

    public Employee (String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}