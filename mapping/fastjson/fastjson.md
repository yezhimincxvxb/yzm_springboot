## 字符

```text
{} 双括号表示对象
[] 中括号表示数组
"" 双引号内是属性或值
: 冒号表示后者是前者的值(这个值可以是字符串、数字、也可以是另一个数组或对象)
{"name": "Michael"} 可以理解为是一个包含name为Michael的对象
[{"name": "Michael"},{"name": "Jerry"}]就表示包含两个对象的数组
{"name":["Michael","Jerry"]}这是一个拥有一个name数组的对象

```

## JSON常用方法

```text
public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray
public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject
public static final <T> T parseObject(String text, Class<T> clazz); // 把JSON文本parse为JavaBean
public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray
public static final <T> List<T> parseArray(String text, Class<T> clazz); //把JSON文本parse成JavaBean集合
public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本
public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本
public static final Object toJSON(Object javaObject); //将JavaBean转换为JSONObject或者JSONArray。
```

## SerializerFeature属性：

```text
QuoteFieldNames：输出key时是否使用双引号,默认为true
UseSingleQuotes：使用单引号而不是双引号,默认为false
WriteMapNullValue：是否输出值为null的字段,默认为false
WriteEnumUsingToString：Enum输出name()或者original,默认为false
SortField：按字段名称排序后输出。默认为false
WriteTabAsSpecial：把\t做转义输出，默认为false
PrettyFormat：结果是否格式化,默认为false
WriteClassName：序列化时写入类型信息，默认为false。反序列化是需用到
```
# @JSONType 注解在类上
```text
@JSONType(orders ={"id", "sex"})
序列化时，对指定的字段排序

@JSONType(ignores ={"id", "sex"})
序列化时，忽略指定的字段

@JSONType(includes ={"id", "sex"})
序列化时，只序列化指定的字段



@JSONType(orders ={"id", "sex"})
序列化时，对指定的字段排序
```
# @JSONField 注解在属性上
```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface JSONField {
    // 配置序列化和反序列化的顺序
    int ordinal() default 0;

    // 序列化和反序列化时候的别名
    String name() default "";

    // 指定字段的格式，对⽇期格式有⽤
    String format() default "";

    // 是否支持序列化
    boolean serialize() default true;

    // 是否支持反序列化
    boolean deserialize() default true;

    // 序列化选项 SerializerFeature.WriteNullNumberAsZero 如空Number填充0
    SerializerFeature[] serialzeFeatures() default {};

    // 反序列化选项
    Feature[] parseFeatures() default {};

    // 标签
    String label() default "";

    // 当你有⼀个字段是json字符串的数据，你希望直接输出，⽽不是经过转义之后再输出。
    boolean jsonDirect() default false;

    // 属性的序列化类，可定制。可有现存的，比如本来是Long，序列化的时候转为String：serializeUsing= ToStringSerializer.class
    Class<?> serializeUsing() default Void.class;

    // 属性的反序列化类，可定制。
    Class<?> deserializeUsing() default Void.class;

    // 参与反序列化时候的别名
    String[] alternateNames() default {};

    // 对象映射到父对象上。不进行子对象映射。简单而言，就是属性为对象的时候，属性对象里面的属性直接输出当做父对象的属性输出
    boolean unwrapped() default false;

    // 设置默认值
    String defaultValue() default "";
}
```