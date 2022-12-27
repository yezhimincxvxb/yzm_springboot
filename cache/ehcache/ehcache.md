### 添加依赖
```xml
<!-- Spring Boot 缓存支持启动器 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
<!-- Ehcache 坐标 -->
<dependency>
    <groupId>net.sf.ehcache</groupId>
    <artifactId>ehcache</artifactId>
</dependency>
```
### 创建ehcache.xml

### yml配置
```text
spring:
   cache:
    ehcache:
      config: classpath:ehcache.xml
```

### 开启EhcacheConfig
@EnableCaching

### 实体类需实现序列化

### 注解
```text
@CacheConfig    注解在类上，表示该类所有缓存方法使用统一指定的缓存区
@CacheEvict     注解在方法上，应用到删除数据的方法上，如删除方法，调用方法时会从缓存中移除相应的数据
@CacheAble      注解在方法上，应用到读数据的方法上，如查找方法：先从缓存中读取，如果没有再调用方法获取数据，然后把数据添加到缓存中
@CachePut       注解在方法上，应用到写数据的方法上，如新增/修改方法，调用方法时会自动把相应的数据放入缓存：
```

### 缓存key
```text
#id       直接使用参数名
#p0       p0对应参数列表的第一个参数，以此类推
#user.id  参数是对象时，使用对象属性
#root.    可以点出很多方法
#root.methodName
#result   返回值
```

### 条件缓存
```text
根据运行流程，如下@Cacheable将在执行方法之前( #result还拿不到返回值) 判断condition，如果返回true，则查缓存；
@Cacheable(value = "user", key = "#id", condition = "#id lt 10")
public User conditionFindById(final Long id)

根据运行流程，如下@CachePut将在执行完方法后（#result就能拿到返回值了）判断condition，如果返回true，则放入缓存；
@CachePut(value = "user", key = "#id", condition = "#result.username ne 'zhang'")
public User conditionSave(final User user)

根据运行流程，如下@CachePut将在执行完方法后（#result就能拿到返回值了）判断unless，如果返回false，则放入缓存；（即跟condition相反）
@CachePut(value = "user", key = "#user.id", unless = "#result.username eq 'zhang'")
public User conditionSave2(final User user)

根据运行流程，如下@CacheEvict， beforeInvocation=false表示在方法执行之后调用（#result能拿到返回值了）；且判断condition，如果返回true，则移除缓存；
@CacheEvict(value = "user", key = "#user.id", beforeInvocation = false, condition = "#result.username ne 'zhang'")
public User conditionDelete(final User user)
```

### @Caching
```text
有时候我们可能组合多个Cache注解使用；比如用户新增成功后，我们要添加id-->user；username--->user；email--->user的缓存；
此时就需要@Caching组合多个注解标签了。
@Caching(
    put = {
        @CachePut(value = "user", key = "#user.id"),
        @CachePut(value = "user", key = "#user.username"),
        @CachePut(value = "user", key = "#user.email")
    }
)
public User save(User user) {}
```
