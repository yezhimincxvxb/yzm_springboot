### 常用校验注解
```text
@Null	                                    任意类型	                         验证注解的元素值是null
@NotNull	                                任意类型	                         验证注解的元素值不是null，可以为空
@NotEmpty	                                String、Collection、Map、数组	     验证注解的元素值不为null且不为空（字符串长度不为0、集合大小不为0）
@NotBlank	                                String	                         验证注解的元素值不为空（不为null、去除首尾空格后长度不为0），
不同于@NotEmpty，@NotBlank只应用于字符串且在比较时会去除字符串的首位空格
@Size(min=下限, max=上限)	                字符串、集合、数组等	             验证注解的元素值的在min和max（包含）指定区间之内，如字符长度、集合大小
@Range(min=下限, max=上限)      	            数字类型,String	                 验证注解的元素值在最小值和最大值之间
@Length(min=下限, max=上限)	                String	                         验证注解的元素值长度在min和max区间内
@Valid	                                    任何非原子类型	                     指定递归验证关联的对象；如用户对象中有个地址对象属性，如果想在验证用户对象时一起验证地址对象的话，
在地址对象上加@Valid注解即可级联验证
@Pattern(regexp=正则表达式,flag=标志的模式)	String	                             验证注解的元素值与指定的正则表达式匹配

@AssertFalse	                            布尔类型	                         验证注解的元素值是false
@AssertTrue	                                布尔类型        	                 验证注解的元素值是true
@Min(value=值)	                            数字类型                        	 验证注解的元素值大于等于@Min指定的value值
@Max（value=值）	                            数字类型	                         验证注解的元素值小于等于@Max指定的value值
@DecimalMin(value=值)	                    数字类型	                         验证注解的元素值大于等于@DecimalMin指定的value值
@DecimalMax(value=值)	                    数字类型	                         验证注解的元素值小于等于@DecimalMax指定的value值
@Digits(integer=整数位数, fraction=小数位数)	数字类型	                         验证注解的元素值的整数位数和小数位数上限
@Past	                                    日历、日期类型	                     验证注解的元素值（日期类型）比当前时间早
@Future	                                    日历、日期类型	                     验证注解的元素值（日期类型）比当前时间晚
@Email(regexp=正则表达式,flag=标志的模式)	    String	                         验证注解的元素值是Email，也可以通过regexp和flag指定自定义的email格式
```

### 自定义注解校验 IsPhone

### @Validated
```text
当Controller层中方法参数是一个对象时，需要将@Validated直接放在该对象前，该对象内部的字段才会被校验
当一些约束是直接出现在Controller层中的参数前时，只有将@Validated放在Controller类上时，参数前的约束才会生效。

@Valid属于javax下的，而@Validated属于spring下；
@Valid支持嵌套校验、而@Validated不支持，@Validated支持分组，而@Valid不支持。
```

### 分组 Object.class：必须是一个接口(ValidGroup)
```text
在Bean中可以指定字段验证所属的groups、在请求参数中可以指定应用哪种groups进行验证，只会触发相应的groups进行验证；
若未指定groups则默认属于组javax.validation.groups.Default
@Validated(value = {Insert.class}

@NotBlank(groups = {Insert.class, Update.class}, message = "username不能为空")
private String username;
```

### 异常处理 ValidateExceptionHandler


















