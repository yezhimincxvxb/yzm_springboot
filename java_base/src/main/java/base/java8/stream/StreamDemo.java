package base.java8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {
//        demo01();
//        demo02();
//        demo03();
//        demo04();
//        demo05();
//        demo06();
        demo07();
    }

    /**
     * Stream 创建
     */
    private static void demo01() {
        //数组流
        String[] arr = {"a", "b", "c"};
        Stream<String> arrStream = Arrays.stream(arr);
        arrStream.forEach(System.out::println);
        System.out.println();

        //集合流
        List<String> list = Arrays.asList(arr);
        list.stream().map(String::toUpperCase).forEach(System.out::print);
        System.out.println();

        Stream<String> stream1 = Stream.of("1", "2", "3", "4");
        stream1.forEach(System.out::println);
        System.out.println();

        Stream<Integer> stream2 = Stream.iterate(2, (x) -> 2 * x).limit(10);
        stream2.forEach(System.out::println);
        System.out.println();

        Stream<Double> stream3 = Stream.generate(Math::random).limit(10);
        stream3.forEach(System.out::println);
        System.out.println();

        //IntStream、LongStream、DoubleStream
        IntStream intStream = IntStream.range(18, 100).limit(10);
        intStream.forEach(System.out::println);
    }

    /**
     * 一个流正常流程：创建流 --> 中间操作 --> 终止操作；如果缺少终止操作，该流是不会被执行的
     * 中间操作：
     * peek窥视：1.调试打印元素值2.修改对象元素字段值
     * map映射：改变元素的值或类型，比如int->String
     * filter过滤：筛选符合条件的元素
     * distinct去重
     * skip跳过前几个
     * limit限制元素到下流个数
     * 终止操作：
     * forEach遍历
     * collect收集
     * allMatch全部符合条件
     * anyMatch只需一个满足条件即可
     * noneMatch没有一个符合条件
     * findAny获取任意一个
     * findFirst获取第一个
     * reduce聚合操作，累加累乘
     */
    private static void demo02() {
        Stream<String> stream = Stream.of("5", "2", "6", "4", "1", "8", "2", "9", "7");
        //peek & forEach
//        stream.peek(System.out::println); //没有终止操作不被执行
//        stream.peek(System.out::println).forEach(System.out::println);

        // map
//        System.out.println("映射："+stream.map(Integer::parseInt).map(x -> x * 2).collect(Collectors.toList()));
        //mapToInt、mapToLong、mapToDouble
//        stream.mapToDouble(Integer::parseInt).forEach(System.out::println);

        //filter
//        System.out.println("过滤后：" + stream.map(Integer::parseInt).filter(x -> x >= 5).collect(Collectors.toList()));

        //distinct
//        System.out.println("去重后：" + stream.distinct().collect(Collectors.toList()));

        //skip跳过前几个、限制元素到下流个数
//        System.out.println(stream.skip(2).limit(5).collect(Collectors.toList()));

//        System.out.println("是否全部都大于5：" +stream.map(Integer::parseInt).allMatch(x -> x > 5));
//        System.out.println("是否有一个大于5：" +stream.map(Integer::parseInt).anyMatch(x -> x > 5));
//        System.out.println("是否一个都没有大于5：" +stream.map(Integer::parseInt).noneMatch(x -> x > 5));

//        System.out.println("排序："+stream.sorted().collect(Collectors.toList()));
//        System.out.println("比较器排序："+stream.sorted(String::compareTo).collect(Collectors.toList()));
//        System.out.println("倒序："+stream.sorted(Comparator.reverseOrder()).collect(Collectors.toList()));

        //findAny获取任意一个
//        Optional<String> any = stream.findAny();
//        System.out.println(any.orElse("没有元素"));

//        System.out.println(stream.max(String::compareTo).get());

        System.out.println(stream.map(Integer::parseInt).reduce(Integer::sum).get());
    }

    /**
     * averagingX：求平均值
     * summingX：求总和
     * counting：元素个数
     */
    private static void demo03() {
        List<Book> books = initBooks();

        //获取所有的图书的平均售价
        Double priceAvg = books.stream().collect(Collectors.averagingDouble(item -> item.getPrice() == null ? 0D : item.getPrice()));
        Optional.ofNullable(priceAvg).ifPresent(System.out::println);
        //所有图书的平均页数
        Double pageAvg = books.stream().collect(Collectors.averagingInt(item -> item.getPages() == null ? 0 : item.getPages()));
        Optional.ofNullable(pageAvg).ifPresent(System.out::println);
        //所有图书的总库存
        Long summingLong = books.stream().collect(Collectors.summingLong(item -> item.getInventory() == null ? 0L : item.getInventory()));
        Long summingLong2 = books.stream().mapToLong(item -> item.getInventory() == null ? 0L : item.getInventory()).sum();
        Optional.ofNullable(summingLong).ifPresent(System.out::println);
        Optional.ofNullable(summingLong2).ifPresent(System.out::println);
    }

    /**
     * groupingBy：分组
     * groupingByConcurrent：并发分组
     */
    private static void demo04() {
        List<Book> books = initBooks();

        //根据书籍种类分组
        Map<String, List<Book>> collect = books.stream().collect(Collectors.groupingBy(Book::getCategory));
        //根据书籍种类分组计数
        Map<String, Long> collect1 = books.stream().collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));
        //获取书籍种类的平均值 -默认是hashMap是无序的
        Map<String, Double> sorted = books.stream().collect(Collectors.groupingBy(Book::getCategory, Collectors.averagingDouble(item -> item.getPrice() == null ? 0 : item.getPrice())));
        System.out.println("获取书籍种类的平均值： " + sorted);
        //倒序排列-key的倒序-自定义一个比较器
        Map<String, Double> sortedTreeMapComparator = books.stream().collect(Collectors.groupingBy(Book::getCategory, () -> new TreeMap<>(Comparator.reverseOrder()), Collectors.averagingDouble((item -> item.getPrice() == null ? 0 : item.getPrice()))));
        System.out.println("获取书籍种类的平均值-倒序： " + sortedTreeMapComparator);
        //倒序排列-key的倒序-treeMp指定是降序map，调用descendingMap
        Map<String, Double> sortedTreeMap = books.stream().collect(Collectors.groupingBy(Book::getCategory, () -> new TreeMap().descendingMap(), Collectors.averagingDouble((item -> item.getPrice() == null ? 0 : item.getPrice()))));
        System.out.println("获取书籍种类的平均值-倒序： " + sortedTreeMap);

        ConcurrentMap<String, List<Book>> collect3 = books.stream().collect(Collectors.groupingByConcurrent(Book::getCategory));
        System.out.println("根据书籍种类进行分组： " + collect3);
        ConcurrentMap<String, Long> collect4 = books.stream().collect(Collectors.groupingByConcurrent(Book::getCategory, Collectors.counting()));
        System.out.println("根据书籍种类进行分组并计算数量： " + collect4);
    }

    /**
     * collectingAndThen：分组后，对组元素进行操作
     */
    private static void demo05() {
        List<Book> books = initBooks();

        Map<String, List<Book>> upPrice = books.stream().collect(Collectors.collectingAndThen(Collectors.groupingBy(Book::getAuthor), items -> {
            items.get("张三").forEach(item -> item.setPrice(item.getPrice() == null ? 0 : item.getPrice() + 10000));
            return items;
        }));
        System.out.println("按照作者分组并将张三的书籍涨价: " + upPrice);

        //根据作者分组--去除作者是张三的书籍
        Map<String, List<Book>> author = books.stream().collect(Collectors.collectingAndThen(Collectors.groupingBy(Book::getAuthor), items -> {
            items.remove("张三");
            return items;
        }));
        System.out.println("根据作者分组--去除作者是张三的书籍: " + author);

        //本次结果和上面的结果一致
        Map<String, List<Book>> collect = books.stream().filter(item -> !"张三".equals(item.getAuthor())).collect(Collectors.groupingBy(Book::getAuthor));
        System.out.println(collect);
    }

    /**
     * joining：拼接
     * mapping：
     */
    private static void demo06() {
        List<Book> books = initBooks();

        //增加连接符 - distinct去除重复元素
        String authorsJoin = books.stream().map(Book::getAuthor).distinct().collect(Collectors.joining(","));
        System.out.println("获取作者集合字符串-有连接符： " + authorsJoin);//张三,李四,王五
        String collect = books.stream().map(Book::getAuthor).distinct().collect(Collectors.joining(",", "[", "]"));
        System.out.println("获取作者集合字符串-有连接符-有开始结束符号： " + collect);//[张三,李四,王五]

        List<String> authors = books.stream().collect(Collectors.mapping(Book::getAuthor, Collectors.toList()));
        System.out.println("获取指定字段的集合： " + authors);//[张三, 李四, 王五, 张三, 李四, 王五, 张三, 李四, 王五, 张三, 张三, 张三]

        List<String> authorDistinct = books.stream().map(Book::getAuthor).distinct().collect(Collectors.toList());
        System.out.println("获取指定字段的集合： " + authorDistinct);//[张三, 李四, 王五]
    }

    /**
     * partitioningBy：分区
     */
    private static void demo07() {
        List<Book> books = initBooks();

        Map<Boolean, List<Integer>> collectParti = Stream.of(1, 2, 3, 4)
                .collect(Collectors.partitioningBy(it -> it % 2 == 0));
        System.out.println("collectParti : " + collectParti);


        //查询java类别数据和非java类别数据的map集合
        Map<Boolean, List<Book>> java = books.stream().collect(Collectors.partitioningBy(item -> item.getCategory().equals("Java")));
        System.out.println("Java书籍和非Java书籍的集合： " + java);

        //平均价格
        Map<Boolean, Double> avgPrice = books.stream().collect(Collectors.partitioningBy(item -> item.getCategory().equals("Java"),
                Collectors.averagingDouble(item -> item.getPrice() == null ? 0 : item.getPrice())));
        System.out.println("java书籍和非java书籍的平均价格： " + avgPrice);

        //分类的数量
        Map<Boolean, Long> count = books.stream().collect(Collectors.partitioningBy(item -> item.getCategory().equals("Java"), Collectors.counting()));
        System.out.println("java书籍和非java书籍的书籍数量： " + count);
    }

    /**
     * reducing：
     */
    private static void demo08() {
        List<Book> books = initBooks();

        //获取库存最大的书籍
        Book book = books.stream().collect(Collectors.reducing(BinaryOperator.maxBy(Comparator.comparingLong(Book::getInventory)))).get();
        System.out.println("库存最大的书籍： " + book);

        Book book1 = books.stream().reduce(BinaryOperator.maxBy(Comparator.comparingLong(Book::getInventory))).get();
        System.out.println("库存最大的书籍： " + book1);

        //获取书籍的总库存数量
        Long inventoryC = books.stream().map(Book::getInventory).reduce(0L, (left, right) -> left + right);
        System.out.println("所有书籍的库存： " + inventoryC);

        Long inventory = books.stream().map(Book::getInventory).collect(Collectors.reducing(0L, (left, right) -> left + right));
        System.out.println("所有书籍的库存： " + inventory);

        //获取所有书籍的总页数
        Integer pagesC = books.stream().collect(Collectors.reducing(0, Book::getPages, (left, right) -> left + right));
        System.out.println("所有书籍的页数总和： " + pagesC);

        Integer pages = books.stream().map(Book::getPages).collect(Collectors.reducing(0, (left, right) -> left + right));
        System.out.println("所有书籍的页数总和： " + pages);
    }

    /**
     * 转集合
     */
    private static void demo09() {
        List<Book> books = initBooks();

        //toList
        List<Book> list1 = books.stream().collect(Collectors.toList());//ArrayList
        LinkedList<Book> list2 = books.stream().collect(Collectors.toCollection(LinkedList::new));
        ArrayList<Book> list3 = books.stream().collect(Collectors.toCollection(ArrayList::new));
        CopyOnWriteArrayList<Book> list4 = books.stream().collect(Collectors.toCollection(CopyOnWriteArrayList::new));

        //toSet
        Set<Book> set1 = books.stream().collect(Collectors.toSet());//HashSet
        Set<Book> set2 = books.stream().collect(Collectors.toCollection(HashSet::new));
        Set<Book> set3 = books.stream().collect(Collectors.toCollection(TreeSet::new));

        //toMap
        Map<String, Double> map1 = books.stream().collect(Collectors.toMap(Book::getName, item -> item.getPrice() == null ? 0 : item.getPrice()));
        System.out.println("书名和价格： " + map1);

        Map<String, String> map2 = books.stream().collect(Collectors.toMap(Book::getAuthor, Book::getName, (one, two) -> one));//HashMap
        System.out.println("作者和书名-有重复的选择第一个书名： " + map2);

        ConcurrentHashMap<String, String> curMap1 = books.stream().collect(Collectors.toMap(Book::getAuthor, Book::getName, String::concat, ConcurrentHashMap::new));
        System.out.println("作者和书名-有重复的选择第一个书名-指定返回值类型： " + curMap1);

        ConcurrentMap<String, Double> curMap2 = books.stream().collect(Collectors.toConcurrentMap(Book::getName,
                item -> item.getPrice() == null ? 0 : item.getPrice()));
        System.out.println("书名和价格： " + curMap2);

        //出现相同的key的时候会报错-故而需要指定有相同key时候的处理策略
        ConcurrentMap<String, String> curMap3 = books.stream().collect(Collectors.toConcurrentMap(Book::getAuthor, Book::getName, (left, right) -> left));
        System.out.println("作者和书名： " + curMap3);

        ConcurrentSkipListMap<String, Double> collect2 = books.stream().collect(Collectors.toConcurrentMap(Book::getAuthor,
                item -> item.getPrice() == null ? 0 : item.getPrice(), (a, b) -> a + b, ConcurrentSkipListMap::new));
        System.out.println("作者和书的单价和： " + collect2);
    }


    private static List<Book> initBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("书名1", "Java", "张三", 53.2, 135, 52301L));
        books.add(new Book("书名2", "Java", "李四", 49.3D, 246, 525301L));
        books.add(new Book("书名3", "Java", "王五", 26.3D, 186, 523101L));
        books.add(new Book("书名4", "Python", "张三", 39.3D, 98, 523101L));
        books.add(new Book("书名5", "Python", "李四", 59.3D, 169, 523021L));
        books.add(new Book("书名6", "Python", "王五", 63.3D, 489, 523L));
        books.add(new Book("书名7", "C#", "张三", 72.3D, 984, 752301L));
        books.add(new Book("书名8", "C#", "李四", 48.3D, 532, 9301L));
        books.add(new Book("书名9", "C#", "王五", 47.3D, 587, 5801L));
        books.add(new Book("书名10", "Netty", "张三", 68.3D, 653, 501L));
        books.add(new Book("书名11", "Netty", "张三", 61.3D, 782, 562301L));
        books.add(new Book("书名12", "Netty", "张三", null, 698, 542301L));
        return books;
    }
}

@Data
@AllArgsConstructor
class Book {
    /**
     * 书名
     */
    private String name;
    /**
     * 类型
     */
    private String category;
    /**
     * 作者
     */
    private String author;
    /**
     * 价格
     */
    private Double price;
    /**
     * 页数
     */
    private Integer pages;
    /**
     * 库存数量
     */
    private Long inventory;
}