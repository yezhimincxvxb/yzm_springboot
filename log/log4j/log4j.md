# 使用说明 百度百科搜 log4j

# log4j.rootLogger=INFO,console,file
其语法为：
log4j.rootLogger = [ level ] , appenderName, appenderName, …
```text
其中，level 是日志记录的优先级，分为OFF、FATAL、ERROR、WARN、INFO、DEBUG、ALL或者自定义的级别。
Log4j建议只使用四个级别，优先级从高到低分别是ERROR、WARN、INFO、DEBUG。 通过在这里定义的级别，您可以控制到应用程序中相应级别的日志信息的开关。
比如在这里定义了INFO级别，只有等于及高于这个级别的才进行处理，则应用程序中所有DEBUG级别的日志信息将不被打印出来。
ALL:打印所有的日志，OFF：关闭所有的日志输出。 appenderName就是指定日志信息输出到哪个地方。可同时指定多个输出目的地。
```
# log4j.appender.stdout=org.apache.log4j.ConsoleAppender
此句为定义名为stdout的输出端是哪种类型，可以是
```text
org.apache.log4j.ConsoleAppender（控制台），
org.apache.log4j.FileAppender（文件），
org.apache.log4j.DailyRollingFileAppender（每天产生一个日志文件），
org.apache.log4j.RollingFileAppender（文件大小到达指定尺寸的时候产生一个新的文件）
org.apache.log4j.WriterAppender（将日志信息以流格式发送到任意指定的地方）
```
# log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
```text
org.apache.log4j.HTMLLayout（以HTML表格形式布局），
org.apache.log4j.PatternLayout（可以灵活地指定布局模式），
org.apache.log4j.SimpleLayout（包含日志信息的级别和信息字符串），
org.apache.log4j.TTCCLayout（包含日志产生的时间、线程、类别等等信息）
```
# log4j.appender.console.layout.ConversionPattern=%d{yy/MM/dd HH:mm:ss} %p [%t] %C.%M:%L -- %m%n
格式化日志
Log4J采用类似C语言中的printf函数的打印格式格式化日志信息，打印参数如下：
```text
Log4J采用类似C语言中的printf函数的打印格式格式化日志信息，打印参数如下：
%m 输出代码中指定的消息；
%M 输出打印该条日志的方法名；
%p 输出优先级，即DEBUG，INFO，WARN，ERROR，FATAL；
%r 输出自应用启动到输出该log信息耗费的毫秒数；
%c 输出所属的类目，通常就是所在类的全名；
%t 输出产生该日志事件的线程名；
%n 输出一个回车换行符，Windows平台为"rn”，Unix平台为"n”；
%d 输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式，比如：%d{yyyy-MM-dd HH:mm:ss,SSS}，输出类似：2002-10-18 22:10:28,921；
%l 输出日志事件的发生位置，及在代码中的行数。
```