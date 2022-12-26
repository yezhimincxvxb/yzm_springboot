# 简介
```text
MapStruct是满足JSR269规范的一个Java注解处理器，用于为Java Bean生成类型安全且高性能的映射。它基于编译阶段生成get/set代码，此实现过程中没有反射，不会造成额外的性能损失。
您所要做的就是定义一个mapper接口（@Mapper），该接口用于声明所有必须的映射方法。在编译期间MapStruct会为该接口自动生成实现类。
该实现类使用简单的Java方法调用来映射source-target对象，在此过程中没有反射或类似的行为发生。
```
# 依赖
```xml
<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct</artifactId>
    <version>1.5.0.Beta1</version>
</dependency>

<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct-processor</artifactId>
    <version>1.5.0.Beta1</version>
</dependency>
```
# 注入方式
```text
方式一：ClassLoader 加载方式
@Mapper
public interface ApiMapper {
ApiMapper INSTANCE = Mappers.getMapper(ApiMapper.class);
}
方式二：Spring注入
@Mapper(componentModel = "spring")
public interface ApiMapper {
}
```

# 插件
mapstruct support

# 使用
```text
字段名称完全一致，转换方法不需要添加@Mapping注解
字段名称不一致，指定@Mapping(target = "username", source = "name")即可
目标类字段少于源类没关系，多于源类可以使用忽略属性@Mapping(target = "id", ignore = true)
或者配置全局忽略 unmappedTargetPolicy = ReportingPolicy.IGNORE

defaultValue：默认值
constant：常量
numberFormat：数字类型格式化
dateFormat：时间类型格式化
expression：表达式，可调用自定义类中的方法

@InheritConfiguration(name = "convert") // 继承映射关系
@InheritInverseConfiguration(name = "convert") // 继承逆配置
```


