# 概念
```text
JDBC 就是使用Java语言操作关系型数据库的一套API
全称 (Java Database Connectivity) Java 数据库连接
```
# 本质
```text
官方(sun公司)定义的一套操作所有关系型数据库的规则，即接口
各个数据库厂商去实现这套接口，提供数据库驱动jar包
我们可以使用这套接口(JDBC)编程，真正执行的代码是驱动jar包中的实现类
```
# 好处
```text
各数据库厂商使用相同的接口，Java代码不需要针对不同的数据库分别开发
可随时替换底层数据库，访问数据库的Java代码基本不变  
```
# DriverManager
```text
1、注册驱动
2、获取数据库连接
```
# Connection
```text
1、获取SQL对象
Statement createStatement(); // 普通执行SQL对象
PrepareStatement prepareStatement(sql); //预编译的SQL对象，防止SQL注入
CallableStatement prepareCall(sql); // 执行存储过程的对象
2、事务管理
开启事务：setAutoCommit(boolean) true自动提交事务，false手法提交事务
提交事务：commit()
回滚事务：rollback()    
```
# Statement
```text
执行SQL语句
int executeUpdate(sql);
ResultSet executeQuery(sql);
```
# ResultSet
```text
封装DQL查询语句的结果
boolean next()：将光标从当前位置向前移动一行；判断当前行是否为有效行
xxx getXXX(参数)
```
# PreparedStatement
```text
1、预编译SQL，性能高
2、预防SQL注入：将敏感字符进行转义

开启预编译功能：useServerPrepStmts=true

MySql日志打印功能；
log-output=FILE
general-log=1
general_log_file=""
show-query-log=1
show_query_log_file=""
long_query_time=2
```