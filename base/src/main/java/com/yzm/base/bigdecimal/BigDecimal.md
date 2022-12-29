# 常用构造函数
```text
BigDecimal(int)
创建一个具有参数所指定整数值的对象
BigDecimal(double)
创建一个具有参数所指定双精度值的对象
BigDecimal(long)
创建一个具有参数所指定长整数值的对象
BigDecimal(String)
创建一个具有参数所指定以字符串表示的数值的对象
```
# 常用方法
```text
BigDecimal add(BigDecimal)：BigDecimal对象中的值相加
BigDecimal subtract(BigDecimal)：BigDecimal对象中的值相减
BigDecimal multiply(BigDecimal)：BigDecimal对象中的值相乘
BigDecimal divide(BigDecimal)：BigDecimal对象中的值相除
String toString()：将BigDecimal对象中的值转换成字符串
double doubleValue()：将BigDecimal对象中的值转换成双精度数
float floatValue()：将BigDecimal对象中的值转换成单精度数
long longValue()：将BigDecimal对象中的值转换成长整数
int intValue()：将BigDecimal对象中的值转换成整数
```
# 大小比较
```text
int a = decimal.compareTo(decimal2);

a = -1,表示 decimal 小于 decimal2；
a = 0, 表示 decimal 等于 decimal2；
a = 1, 表示 decimal 大于 decimal2；
```
# 格式化
```text
NumberFormat：
DecimalFormat：
```