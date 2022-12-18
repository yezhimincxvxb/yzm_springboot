# 数据库连接池
```text
数据库连接池是一个容器，负责分配、管理数据库连接
数据库连接可重复使用，不必每次都重新创建
释放空闲时间超过最大空闲时间的数据库连接，避免因没有释放数据库而造成的数据库连接遗落

好处：
资源重用
提升系统响应速度
避免数据库连接遗落
```
# 常见数据库连接池
```text
C3P0
DBCP
Druid：德鲁伊
```
# Druid数据库连接池
```text
Druid是阿里巴巴开源平台上的一个项目，整个项目由数据库连接池、插件框架和SQL解析器组成。该项目主要是为了扩展JDBC的一些限制，
可以让程序员实现一些特殊的需求，比如向密钥服务请求凭证、统计SQL信息、SQL性能收集、SQL注入检查、SQL翻译等，程序员可以通过定制来实现自己需要的功能。
```
# 配置浏览器上预览 DruidConfig
http://localhost:port/druid/index.html

# 数据库密码加密
命令行输入：java -cp druid-1.1.22.jar com.alibaba.druid.filter.config.ConfigTools (数据库正确的密码)
注意：需要下载druid-1.1.22.jar包

输入：
privateKey:MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAzENPPom3zuon5kWXbyoQV54hEN3HlijdY0ALM4KVE9qc8OxCMy2lMohKhZT9uwGm4dsGfJlu9ljDEbN9yHrJwQIDAQABAkAc/5FId5S4s1Vmw4p0JaycQ769/j5Pew2zAWyVq3wujSGbNq6GoGKgfHGg91zFdrjv17S++LktvyH8K35xyTThAiEA87umkaD3OV8R7NWH600QqgXGpYgzNxrCdYBqOvpfH7sCIQDWixx6tzkHNQBSwMDAocsryOlUOV3Zd90wa7cHcowuswIgMRsZkDWyGakB34mV/N5mARtwzmEGkI+fpjTdMkzHZYkCIQDOrUiMN0Gq3bYoAiQt7i8pSOzuBC2St9PPN5UkiYbd3QIhALzvI0hcP6ESrXkClH+7RjcBlplVzB/joBI6tdWWiafN
publicKey:MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMxDTz6Jt87qJ+ZFl28qEFeeIRDdx5Yo3WNACzOClRPanPDsQjMtpTKISoWU/bsBpuHbBnyZbvZYwxGzfch6ycECAwEAAQ==
password:N+Q4wEnh8P1HgYRafJPx3EFmDNOySmltWWujdyVtzPrmnNAXWg6D49ai2Tt9eRuJbqnO+AcEBmwV2aNAcj1sMg==

在配置文件中：
publicKey:MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMxDTz6Jt87qJ+ZFl28qEFeeIRDdx5Yo3WNACzOClRPanPDsQjMtpTKISoWU/bsBpuHbBnyZbvZYwxGzfch6ycECAwEAAQ==
password:N+Q4wEnh8P1HgYRafJPx3EFmDNOySmltWWujdyVtzPrmnNAXWg6D49ai2Tt9eRuJbqnO+AcEBmwV2aNAcj1sMg==

spring:
datasource:
druid:
password: ${password}
filters: config
filter:
config:
enabled: true
connection-properties: config.decrypt=true;config.decrypt.key=${publickey}

###配置参数
配置	                    缺省值	                                                说明
name		                                                配置这个属性的意义在于，如果存在多个数据源，监控的时候可以通过名字来区分开来。
如果没有配置，将会生成一个名字，格式是："DataSource-" + System.identityHashCode(this).
另外配置此属性至少在1.0.5版本中是不起作用的，强行设置name会出错。详情-点此处。
url		                                                    连接数据库的url，不同数据库不一样。例如：
mysql : jdbc:mysql://10.20.153.104:3306/druid2
oracle : jdbc:oracle:thin:@10.20.149.85:1521:ocnauto
username		                                            连接数据库的用户名
password		                                            连接数据库的密码。如果你不希望密码直接写在配置文件中，可以使用ConfigFilter。
driverClassName	                                            根据url自动识别	这一项可配可不配，如果不配置druid会根据url自动识别dbType，然后选择相应的driverClassName
initialSize	                 0	                            初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时
maxActive	                 8	                            最大连接池数量
maxIdle	                     8	                            已经不再使用，配置了也没效果
minIdle		                                                最小连接池数量
maxWait		                                                获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
poolPreparedStatements	   false	                        是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
maxPoolPreparedStatementPerConnectionSize	-1	            要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
validationQuery		                                        用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。
validationQueryTimeout		                                单位：秒，检测连接是否有效的超时时间。底层调用jdbc Statement对象的void setQueryTimeout(int seconds)方法
testOnBorrow	            true	                        申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
testOnReturn	            false	                        归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
testWhileIdle	            false	                        建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
keepAlive	                false （1.0.28）	            连接池中的minIdle数量以内的连接，空闲时间超过minEvictableIdleTimeMillis，则会执行keepAlive操作。
timeBetweenEvictionRunsMillis	1分钟（1.0.14）	            有两个含义：
1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。
2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
numTestsPerEvictionRun	30分钟（1.0.14）	                    不再使用，一个DruidDataSource只支持一个EvictionRun
minEvictableIdleTimeMillis		                            连接保持空闲而不被驱逐的最小时间
connectionInitSqls		                                    物理连接初始化的时候执行的sql
exceptionSorter	                                            根据dbType自动识别	当数据库抛出一些不可恢复的异常时，抛弃连接
filters		                                                属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：
监控统计用的filter:stat
日志用的filter:log4j
防御sql注入的filter:wall
proxyFilters		                                        类型是List<com.alibaba.druid.filter.Filter>，如果同时配置了filters和proxyFilters，是组合关系，并非替换关系

