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
