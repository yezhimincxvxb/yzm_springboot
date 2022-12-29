package com.yzm.base.java8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo02 {
    static List<Person> persons = new ArrayList<>();

    static {
        persons.add(new Person("王小明", 15));
        persons.add(new Person("李大刚", 16));
        persons.add(new Person("柱子", 19));
        persons.add(new Person("张三", 16));
    }

    public static void main(String[] args) {
//        demo01();
//        demo02();
//        demo03();
//        demo04();

        String[] arr = {"z, h, a, n, g", "s, a, n"};
        List<String> list = Arrays.asList(arr);
        System.out.println(list);
        List<String> collect = list.stream().flatMap(x -> {
            String[] array = x.split(",");
            return Arrays.stream(array);
        }).collect(Collectors.toList());
        System.out.println(collect);
    }

    public static void demo04() {
        Map<String, Integer> map1 = persons.stream().collect(Collectors.toMap(Person::getName, Person::getAge));
        System.out.println("map1 = " + map1);
        Map<String, Person> map2 = persons.stream().collect(Collectors.toMap(Person::getName, person -> person));
        System.out.println("map2 = " + map2);
        Map<Integer, Person> map3 = persons.stream().collect(Collectors.toMap(Person::getAge, person -> person, (k1, k2) -> k1));
        System.out.println("map3 = " + map3);
        Map<String, Integer> map4 = persons.stream().collect(HashMap::new, (map, p) -> map.put(p.getName(), p.getAge()), Map::putAll);
        System.out.println("map4 = " + map4);

        Map<Integer, List<Person>> map5 = persons.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println("map5 = " + map5);
        Map<Integer, List<String>> map6 = persons.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName,Collectors.toList())));
        System.out.println("map6 = " + map6);

        Map<String, Integer> map7 = new HashMap<>();
        persons.add(new Person("柱子", 29));
        for (Person person : persons) {
            //key相同，value累加
            map7.merge(person.getName(), person.getAge(), Integer::sum);
        }
        System.out.println("map7 = " + map7);
    }

    public static void demo03() {
        Map<String, Integer> map = new HashMap<>();
        map.put("露西", 16);
        map.put("黛安娜", 17);

        List<Person> list = map.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getKey()))
                .map(e -> new Person(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        List<Person> list2 = map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> new Person(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        System.out.println("list = " + list);
        System.out.println("list2 = " + list2);
    }

    public static void demo02() {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(3, 4, 5, 2, 7));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(3, 2, 7, 6, 1, 8));

        //交集
//        List<Integer> intersection = list1.stream().filter(list2::contains).collect(Collectors.toList());
//        System.out.println("交集 ：" + intersection);

        //并集
//        list1.addAll(list2);
//        List<Integer> union = list1.stream().distinct().collect(Collectors.toList());
//        System.out.println("并集：" + union);

        //差集
        //(list1 - list2)
        List<Integer> difference = list1.stream().filter(item -> !list2.contains(item)).collect(Collectors.toList());
        System.out.println("差集：" + difference);
        List<Integer> difference2 = list2.stream().filter(item -> !list1.contains(item)).collect(Collectors.toList());
        System.out.println("差集：" + difference2);

        Set<Integer> set = new HashSet<>();
    }

    //将一个对象的集合转化成另一个对象的集合
    public static void demo01() {
        System.out.println("persons = " + persons);

        List<Student> students = persons.stream()
                .map(person -> new Student(person.getName(), person.getAge() + 1))
                .collect(Collectors.toList());
        System.out.println("students = " + students);
    }
}

@Data
@AllArgsConstructor
class Person {
    private String name;
    private Integer age;
}

@Data
@AllArgsConstructor
class Student {
    private String name;
    private Integer age;
}