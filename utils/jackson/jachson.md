# 注解说明
常用：
```text
@JsonSerialize：
用于指定一个自定义序列化器(custom serializer)来序列化实体例的某属性

@JsonDeserialize：
自定义反序列化器(custom deserializer)

@JsonAlias：
在反序列化过程中为属性定义一个或多个别名

@JsonIgnoreProperties
注解在类上，标记一个或多个属性被Jackson忽略

@JsonIgnore
注解在属性上，标明一个属性可以被Jackson忽略

@JsonInclude
注解在类上或属性上，序列化时会排除属性值是空值（empty或null）、没有默认值的属性

@JsonProperty
在非标准的setter和getter方法上使用该注解, 可以成功序列化和反序列化

@JsonFormat
指定序列化日期和时间时的格式

@JsonUnwrapped
指定值在序列化和反序列化时, 去除对应属性的外包装(根节点)
```
不常用：
```text
@JsonAnyGetter：
序列化时，将Map类型属性中的key/value当做标准属性输入

@JsonAnySetter：
反序列化时，从Json字符串得到的属性值会加入到map属性中

@JsonGetter：
用来标记一个方法是指定属性的getter方法

@JsonSetter：
用来标记一个方法是指定属性的setter方法

@JsonPropertyOrder：
注解在类上，指定实体属性序列化后的顺序

@JsonRawValue：
序列化时把属性的值原样输出(会帮转义)

@JsonValue：
只用被注解的方法才会被序列化

@JsonRootName：
注解在类上，序列化后的结果再被指定名称包裹

@JacksonInject
指明一个属性的值是通过注入得到而不是从Json字符串反序列得到

@JsonIgnoreType
注解在内部类上，标明以该类为类型的属性都会被Jackson忽略
```