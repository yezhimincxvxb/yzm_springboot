```text
%d{HH:mm:ss.SSS} 表示输出到毫秒的时间
%t 输出当前线程名称
%-5level 输出日志级别，-5表示左对齐并且固定输出5个字符，如果不足在右边补0
%logger 输出logger名称，因为Root Logger没有名称，所以没有输出
%msg 日志文本
%n 换行
%F 输出所在的类文件名，如Log4j2Test.java
%L 输出行号
%M 输出所在方法名
%l 输出语句所在的行数, 包括类名、方法名、文件名、行数

```