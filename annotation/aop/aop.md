# 切点表达式
execution：推荐使用
```text
由于Spring切面粒度最小是达到方法级别，而execution表达式可以用于明确指定方法返回类型，类名，方法名和参数名等与方法相关的部件，并且在Spring中，
大部分需要使用AOP的业务场景也只需要达到方法级别即可，因而execution表达式的使用是最为广泛的。如下是execution表达式的语法：
execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)
这里问号表示当前项可以有也可以没有，其中各项的语义如下：
modifiers-pattern：方法的可见性，如public，protected；
ret-type-pattern：方法的返回值类型，如int，void等；
declaring-type-pattern：方法所在类的全路径名，如com.spring.Aspect；
name-pattern：方法名类型，如addUser()；
param-pattern：方法的参数类型，如java.lang.String；
throws-pattern：方法抛出的异常类型，如java.lang.Exception；
如下是一个使用execution表达式的例子：
execution(public * com.spring.service.UserService.addUser(java.lang.String,..))
上述切点表达式将会匹配使用public修饰，返回值为任意类型，并且是com.spring.service.UserService类中名称为addUser的方法，方法可以有多个参数，
但是第一个参数必须是java.lang.String类型的方法。上述示例中我们使用了..通配符，关于通配符的类型，主要有两种：
*通配符，该通配符主要用于匹配单个单词，或者是以某个词为前缀或后缀的单词。
下述示例表示返回值为任意类型，在com.spring.service.UserService类中，并且参数个数为零的方法：
execution(* com.spring.service.UserService.*())
下述示例表示返回值为任意类型，在com.spring.service包中，以User为前缀的类，并且是类中参数个数为零方法：
execution(* com.spring.service.User*.*())
..通配符，该通配符表示0个或多个项，主要用于declaring-type-pattern和param-pattern中，如果用于declaring-type-pattern中，则表示匹配当前包及其子包，
如果用于param-pattern中，则表示匹配0个或多个参数。
如下示例表示匹配返回值为任意类型，并且是com.spring.service包及其子包下的任意类的名称为findUser的方法，而且该方法不能有任何参数：
execution(* com.spring.service..*.findUser())
这里需要说明的是，包路径service..*.findUser()中的..应该理解为延续前面的service路径，表示到service路径为止，或者继续延续service路径，从而包括其子包路径；
后面的*.findUser()，这里的*表示匹配一个单词，因为是在方法名前，因而表示匹配任意的类。
如下示例是使用..表示任意个数的参数的示例，需要注意，表示参数的时候可以在括号中事先指定某些类型的参数，而其余的参数则由..进行匹配：
execution(* com.spring.service.BusinessObject.businessService(java.lang.String,..))
```
@annotation：(注解在方法上) 推荐使用
```text
@annotation的使用方式与@within的相似，表示匹配使用@annotation指定注解标注的方法将会被环绕，其使用语法如下：
@annotation(annotation-type)
如下示例表示匹配使用com.spring.annotation.UserLogin注解标注的方法：
@annotation(com.spring.annotation.UserLogin)
```
within：(不推荐使用)
```text
within表达式的粒度为类，其参数为全路径的类名（可使用通配符），表示匹配当前表达式的所有类都将被当前方法环绕。如下是within表达式的语法：
within(declaring-type-pattern)
within表达式只能指定到类级别，如下示例表示匹配com.spring.service.UserService中的所有方法：
within(com.spring.service.UserService)
within表达式路径和类名都可以使用通配符进行匹配，比如如下表达式将匹配com.spring.service包下的所有类，不包括子包中的类：
within(com.spring.service.*)
如下表达式表示匹配com.spring.service包及子包下的所有类：
within(com.spring.service..*)
```
@within：(注解在类上) (不推荐使用)
```text
前面我们讲解了within的语义表示匹配指定类型的类实例，这里的@within表示匹配带有指定注解的类，其使用语法如下所示：
@within(annotation-type)
如下所示示例表示匹配使用com.spring.annotation.UserLogin注解标注的类：
@within(com.spring.annotation.UserLogin)
```
args：(不推荐使用)
```text
args表达式的作用是匹配指定参数类型和指定参数数量的方法，无论其类路径或者是方法名是什么。这里需要注意的是，args指定的参数必须是全路径的。如下是args表达式的语法：
args(param-pattern)
如下示例表示匹配所有只有一个参数，并且参数类型是java.lang.String类型的方法：
args(java.lang.String)
也可以使用通配符，但这里通配符只能使用..，而不能使用*。如下是使用通配符的实例，该切点表达式将匹配第一个参数为java.lang.String，
最后一个参数为java.lang.Integer，并且中间可以有任意个数和类型参数的方法：
args(java.lang.String,..,java.lang.Integer)
```
@args：(不推荐使用)
```text
@within和@annotation分别表示匹配使用指定注解标注的类和标注的方法将会被匹配，@args则表示使用指定注解标注的类作为某个方法的参数时该方法将会被匹配。
如下是@args注解的语法：
@args(annotation-type)
如下示例表示匹配使用了com.spring.annotation.UserId注解标注的类作为参数的方法：
@args(com.spring.annotation.UserId)
```
this和target：(不推荐使用)
```text
this和target需要放在一起进行讲解，主要目的是对其进行区别。this和target表达式中都只能指定类或者接口，在面向切面编程规范中，
this表示匹配调用当前切点表达式所指代对象方法的对象，target表示匹配切点表达式指定类型的对象
```