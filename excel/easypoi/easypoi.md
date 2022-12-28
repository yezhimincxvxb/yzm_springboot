### 概述
导出导入Excel表格

### 导入依赖
```xml
<!-- easypoi -->
<dependency>
    <groupId>cn.afterturn</groupId>
    <artifactId>easypoi-base</artifactId>
    <version>3.2.0</version>
</dependency>
<dependency>
    <groupId>cn.afterturn</groupId>
    <artifactId>easypoi-web</artifactId>
    <version>3.2.0</version>
</dependency>
<dependency>
    <groupId>cn.afterturn</groupId>
    <artifactId>easypoi-annotation</artifactId>
    <version>3.2.0</version>
</dependency>
或者
<!--excel操作-->
<dependency>
    <groupId>cn.afterturn</groupId>
    <artifactId>easypoi-spring-boot-starter</artifactId>
    <version>4.4.0</version>
</dependency>
```

### 注解说明
```text
easypoi起因就是Excel的导入导出,最初的模板是实体和Excel的对应,entity--row,filed--col 这样利用注解我们可以和容易做到excel到导入导出 经过一段时间发展,
现在注解有5个类分别是

@Excel 作用到filed上面,是对Excel一列的一个描述
@ExcelCollection 表示一个集合,主要针对一对多的导出,比如一个老师对应多个科目,科目就可以用集合表示
@ExcelEntity 表示一个继续深入导出的实体,但他没有太多的实际意义,只是告诉系统这个对象里面同样有导出的字段
@ExcelIgnore 和名字一样表示这个字段被忽略跳过这个导出
@ExcelTarget 这个是作用于最外层的对象,描述这个对象的id,以便支持一个对象可以针对不同导出做出不同处理
```
### @Excel
```text
属性	             类型	    默认值	        功能
name            String      null        列名,支持name_id
orderNum        String      "0"         列的排序,支持name_id
needMerge       boolean     false       是否需要纵向合并单元格(用于实体类有集合时)
mergeVertical   boolean     false       纵向合并内容相同的单元格
replace         String[]    {}          值得替换  导出是{a_id,b_id} 导入反过来
format          String      ""          时间格式化
numFormat       String      ""          数字格式化
suffix          String      ""          文字后缀,如% 90 变成90%
groupName       String      ""          分组
```

### @ExcelTarget
```text
属性	        类型	    默认值	    功能
value           String      null        定义ID
@ExcelTarget("teacher")
class UserA implements Serializable {}
```

### ExcelEntity
```text
属性	        类型	    默认值	    功能
id              String      ""          定义ID
name            String      ""
show            boolean     false
@ExcelEntity(name = "宠物猫", show = true)
private Cat cat;
```

### ExcelCollection
```text
属性	        类型	    默认值	            功能
id              String      null                定义ID
name            String      null                定义集合列名,支持nanm_id
orderNum        String      "0"                 排序,支持name_id
type            Class<?>    ArrayList.class     List集合元素的字节码对象
@ExcelCollection(name = "宠物狗", orderNum = "3", type = Dog.class)
private List<Dog> dogs;
```



