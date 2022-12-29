### 官方网址

https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html

### 目录结构

```text
resources
    msg
    static(静态资源) 可以直接通过 http://localhost:8080/hello.html 访问
    templates(模板文件)
```

### 常用表达式

```text
#{} 消息表达式
${} 变量表达式
*{} 选择表达式
@{} 链接url表达式
~{} 片段表达式
[[...]] 内联表达式，经转义后输出(等同于th:text)
[(...)] 内联表达式，原文直接输出(等同于th:utext)
```

### 基础操作

文字

```text
    文本字面量：'one text', 'Another one!',...  文本文字只是在单引号之间指定的字符串。它们可以包含任何字符，但您应该使用\'.
    数字字面量：0, 34, 3.0, 12.3,...            数字文字就是：数字。
    布尔文字：true,false                        布尔文字是true和false
    空字面量： null
    文字标记：one, sometext, main,...           数字、布尔和空文字实际上是文字标记的特例
                                              这些标记允许在标准表达式中进行一些简化。它们的工作方式与文本字面量 ( '...')完全相同，但它们只允许使用字母
                                              ( A-Zand a-z)、数字 ( 0-9)、方括号 ( [and ])、点 ( .)、连字符 ( -) 和下划线 ( _)。所以没有空格，没有逗号等。
```

文字操作：

```text
    字符串连接： +                              文本，无论是文字还是变量或消息表达式的计算结果，都可以使用+运算符轻松附加：
    字面替换： |The name is ${name}|           文字替换允许轻松格式化包含变量值的字符串，而无需在文字后附加'...' + '...'.这些替换必须用竖线 | 包围
             'The name is' + ${user.name}
```

算术运算：

```text
二元运算符：+, -, *, /,%
减号（一元运算符）： -
```

布尔运算：

```text
二元运算符：and,or
布尔否定（一元运算符）：!,not
```

比较与相等：

```text
比较器：>(gt) <(lt) >=(ge) <=(le)
等式运算符：==(eq)  !=(ne)
```

条件运算符：

```text
如果-那么： (if) ? (then)
如果-然后-其他： (if) ? (then) : (else)
默认： (value) ?: (defaultValue)
```

### 表达式基本对象

```text
#ctx: 上下文对象。
#vars: 上下文变量。
#locale: 上下文语言环境。
#request：（仅在 Web 上下文中）HttpServletRequest对象。
#response：（仅在 Web 上下文中）HttpServletResponse对象。
#session：（仅在 Web 上下文中）HttpSession对象。
#servletContext：（仅在 Web 上下文中）ServletContext对象。
```

### 表达式实用程序对象

# execInfo：表达式对象，提供有关在 Thymeleaf 标准表达式中处理的模板的有用信息。

# messages: 用于获取变量表达式内的外部化消息的实用方法，与使用#{...}语法获取它们的方式相同。

```text
${#messages.msg('msgKey')}
${#messages.msg('msgKey', param1)}
${#messages.msg('msgKey', param1, param2)}
${#messages.msg('msgKey', param1, param2, param3)}
${#messages.msgWithParams('msgKey', new Object[] {param1, param2, param3, param4})}
${#messages.arrayMsg(messageKeyArray)}
${#messages.listMsg(messageKeyList)}
${#messages.setMsg(messageKeySet)}
${#messages.msgOrNull('msgKey')}
${#messages.msgOrNull('msgKey', param1)}
${#messages.msgOrNull('msgKey', param1, param2)}
${#messages.msgOrNull('msgKey', param1, param2, param3)}
${#messages.msgOrNullWithParams('msgKey', new Object[] {param1, param2, param3, param4})}
${#messages.arrayMsgOrNull(messageKeyArray)}
${#messages.listMsgOrNull(messageKeyList)}
${#messages.setMsgOrNull(messageKeySet)}
```

# uris: 用于在 Thymeleaf 标准表达式中执行 URI/URL 操作（尤其是转义/非转义）的实用程序对象。

# conversions：实用程序对象，允许在模板的任何点执行转换服务

```text
${#conversions.convert(object, 'java.util.TimeZone')}
${#conversions.convert(object, targetClass)}
```

# dates：java.util.Date对象的实用方法：

```text
${#dates.format(date)}
${#dates.arrayFormat(datesArray)}
${#dates.listFormat(datesList)}
${#dates.setFormat(datesSet)}
${#dates.formatISO(date)}
${#dates.arrayFormatISO(datesArray)}
${#dates.listFormatISO(datesList)}
${#dates.setFormatISO(datesSet)}
${#dates.format(date, 'dd/MMM/yyyy HH:mm')}
${#dates.arrayFormat(datesArray, 'dd/MMM/yyyy HH:mm')}
${#dates.listFormat(datesList, 'dd/MMM/yyyy HH:mm')}
${#dates.setFormat(datesSet, 'dd/MMM/yyyy HH:mm')}
${#dates.day(date)}                    // also arrayDay(...), listDay(...), etc.
${#dates.month(date)}                  // also arrayMonth(...), listMonth(...), etc.
${#dates.monthName(date)}              // also arrayMonthName(...), listMonthName(...), etc.
${#dates.monthNameShort(date)}         // also arrayMonthNameShort(...), listMonthNameShort(...), etc.
${#dates.year(date)}                   // also arrayYear(...), listYear(...), etc.
${#dates.dayOfWeek(date)}              // also arrayDayOfWeek(...), listDayOfWeek(...), etc.
${#dates.dayOfWeekName(date)}          // also arrayDayOfWeekName(...), listDayOfWeekName(...), etc.
${#dates.dayOfWeekNameShort(date)}     // also arrayDayOfWeekNameShort(...), listDayOfWeekNameShort(...), etc.
${#dates.hour(date)}                   // also arrayHour(...), listHour(...), etc.
${#dates.minute(date)}                 // also arrayMinute(...), listMinute(...), etc.
${#dates.second(date)}                 // also arraySecond(...), listSecond(...), etc.
${#dates.millisecond(date)}            // also arrayMillisecond(...), listMillisecond(...), etc.
${#dates.create(year,month,day)}
${#dates.create(year,month,day,hour,minute)}
${#dates.create(year,month,day,hour,minute,second)}
${#dates.create(year,month,day,hour,minute,second,millisecond)}
${#dates.createNow()}
${#dates.createNowForTimeZone()}
${#dates.createToday()}
${#dates.createTodayForTimeZone()}
```

# calendars: 类似于#dates，但对于java.util.Calendar对象：

```text
${#calendars.format(cal)}
${#calendars.arrayFormat(calArray)}
${#calendars.listFormat(calList)}
${#calendars.setFormat(calSet)}
${#calendars.formatISO(cal)}
${#calendars.arrayFormatISO(calArray)}
${#calendars.listFormatISO(calList)}
${#calendars.setFormatISO(calSet)}
${#calendars.format(cal, 'dd/MMM/yyyy HH:mm')}
${#calendars.arrayFormat(calArray, 'dd/MMM/yyyy HH:mm')}
${#calendars.listFormat(calList, 'dd/MMM/yyyy HH:mm')}
${#calendars.setFormat(calSet, 'dd/MMM/yyyy HH:mm')}
${#calendars.day(date)}                // also arrayDay(...), listDay(...), etc.
${#calendars.month(date)}              // also arrayMonth(...), listMonth(...), etc.
${#calendars.monthName(date)}          // also arrayMonthName(...), listMonthName(...), etc.
${#calendars.monthNameShort(date)}     // also arrayMonthNameShort(...), listMonthNameShort(...), etc.
${#calendars.year(date)}               // also arrayYear(...), listYear(...), etc.
${#calendars.dayOfWeek(date)}          // also arrayDayOfWeek(...), listDayOfWeek(...), etc.
${#calendars.dayOfWeekName(date)}      // also arrayDayOfWeekName(...), listDayOfWeekName(...), etc.
${#calendars.dayOfWeekNameShort(date)} // also arrayDayOfWeekNameShort(...), listDayOfWeekNameShort(...), etc.
${#calendars.hour(date)}               // also arrayHour(...), listHour(...), etc.
${#calendars.minute(date)}             // also arrayMinute(...), listMinute(...), etc.
${#calendars.second(date)}             // also arraySecond(...), listSecond(...), etc.
${#calendars.millisecond(date)}        // also arrayMillisecond(...), listMillisecond(...), etc.
${#calendars.create(year,month,day)}
${#calendars.create(year,month,day,hour,minute)}
${#calendars.create(year,month,day,hour,minute,second)}
${#calendars.create(year,month,day,hour,minute,second,millisecond)}
${#calendars.createForTimeZone(year,month,day,timeZone)}
${#calendars.createForTimeZone(year,month,day,hour,minute,timeZone)}
${#calendars.createForTimeZone(year,month,day,hour,minute,second,timeZone)}
${#calendars.createForTimeZone(year,month,day,hour,minute,second,millisecond,timeZone)}
${#calendars.createNow()}
${#calendars.createNowForTimeZone()}
${#calendars.createToday()}
${#calendars.createTodayForTimeZone()}
```

# numbers：数字对象的实用方法：

```text
${#numbers.formatInteger(num,3)}
${#numbers.arrayFormatInteger(numArray,3)}
${#numbers.listFormatInteger(numList,3)}
${#numbers.setFormatInteger(numSet,3)}
${#numbers.formatInteger(num,3,'POINT')}
${#numbers.arrayFormatInteger(numArray,3,'POINT')}
${#numbers.listFormatInteger(numList,3,'POINT')}
${#numbers.setFormatInteger(numSet,3,'POINT')}
${#numbers.formatDecimal(num,3,2)}
${#numbers.arrayFormatDecimal(numArray,3,2)}
${#numbers.listFormatDecimal(numList,3,2)}
${#numbers.setFormatDecimal(numSet,3,2)}
${#numbers.formatDecimal(num,3,2,'COMMA')}
${#numbers.arrayFormatDecimal(numArray,3,2,'COMMA')}
${#numbers.listFormatDecimal(numList,3,2,'COMMA')}
${#numbers.setFormatDecimal(numSet,3,2,'COMMA')}
${#numbers.formatDecimal(num,3,'POINT',2,'COMMA')}
${#numbers.arrayFormatDecimal(numArray,3,'POINT',2,'COMMA')}
${#numbers.listFormatDecimal(numList,3,'POINT',2,'COMMA')}
${#numbers.setFormatDecimal(numSet,3,'POINT',2,'COMMA')}
${#numbers.formatCurrency(num)}
${#numbers.arrayFormatCurrency(numArray)}
${#numbers.listFormatCurrency(numList)}
${#numbers.setFormatCurrency(numSet)}
${#numbers.formatPercent(num)}
${#numbers.arrayFormatPercent(numArray)}
${#numbers.listFormatPercent(numList)}
${#numbers.setFormatPercent(numSet)}
${#numbers.formatPercent(num, 3, 2)}
${#numbers.arrayFormatPercent(numArray, 3, 2)}
${#numbers.listFormatPercent(numList, 3, 2)}
${#numbers.setFormatPercent(numSet, 3, 2)}
${#numbers.sequence(from,to)}
${#numbers.sequence(from,to,step)}
```

# strings：String对象的方法：

```text
${#strings.toString(obj)}                           // also array*, list* and set*
${#strings.isEmpty(name)}
${#strings.arrayIsEmpty(nameArr)}
${#strings.listIsEmpty(nameList)}
${#strings.setIsEmpty(nameSet)}
${#strings.defaultString(text,default)}
${#strings.arrayDefaultString(textArr,default)}
${#strings.listDefaultString(textList,default)}
${#strings.setDefaultString(textSet,default)}
${#strings.contains(name,'ez')}                     // also array*, list* and set*
${#strings.containsIgnoreCase(name,'ez')}           // also array*, list* and set*
${#strings.startsWith(name,'Don')}                  // also array*, list* and set*
${#strings.endsWith(name,endingFragment)}           // also array*, list* and set*
${#strings.indexOf(name,frag)}                      // also array*, list* and set*
${#strings.substring(name,3,5)}                     // also array*, list* and set*
${#strings.substringAfter(name,prefix)}             // also array*, list* and set*
${#strings.substringBefore(name,suffix)}            // also array*, list* and set*
${#strings.replace(name,'las','ler')}               // also array*, list* and set*
${#strings.prepend(str,prefix)}                     // also array*, list* and set*
${#strings.append(str,suffix)}                      // also array*, list* and set*
${#strings.toUpperCase(name)}                       // also array*, list* and set*
${#strings.toLowerCase(name)}                       // also array*, list* and set*
${#strings.arrayJoin(namesArray,',')}
${#strings.listJoin(namesList,',')}
${#strings.setJoin(namesSet,',')}
${#strings.arraySplit(namesStr,',')}                // returns String[]
${#strings.listSplit(namesStr,',')}                 // returns List<String>
${#strings.setSplit(namesStr,',')}                  // returns Set<String>
${#strings.trim(str)}                               // also array*, list* and set*
${#strings.length(str)}                             // also array*, list* and set*
${#strings.abbreviate(str,10)}                      // also array*, list* and set*
${#strings.capitalize(str)}                         // also array*, list* and set*
${#strings.unCapitalize(str)}                       // also array*, list* and set*
${#strings.capitalizeWords(str)}                    // also array*, list* and set*
${#strings.capitalizeWords(str,delimiters)}         // also array*, list* and set*
${#strings.escapeXml(str)}                          // also array*, list* and set*
${#strings.escapeJava(str)}                         // also array*, list* and set*
${#strings.escapeJavaScript(str)}                   // also array*, list* and set*
${#strings.unescapeJava(str)}                       // also array*, list* and set*
${#strings.unescapeJavaScript(str)}                 // also array*, list* and set*
${#strings.equals(first, second)}
${#strings.equalsIgnoreCase(first, second)}
${#strings.concat(values...)}
${#strings.concatReplaceNulls(nullValue, values...)}
${#strings.randomAlphanumeric(count)}
```

# objects: 一般对象的方法。

```text
${#objects.nullSafe(obj,default)}
${#objects.arrayNullSafe(objArray,default)}
${#objects.listNullSafe(objList,default)}
${#objects.setNullSafe(objSet,default)}
```

# bools：布尔评估的方法。

```text
${#bools.isTrue(obj)}
${#bools.arrayIsTrue(objArray)}
${#bools.listIsTrue(objList)}
${#bools.setIsTrue(objSet)}
${#bools.isFalse(cond)}
${#bools.arrayIsFalse(condArray)}
${#bools.listIsFalse(condList)}
${#bools.setIsFalse(condSet)}
${#bools.arrayAnd(condArray)}
${#bools.listAnd(condList)}
${#bools.setAnd(condSet)}
${#bools.arrayOr(condArray)}
${#bools.listOr(condList)}
${#bools.setOr(condSet)}
```

# arrays: 数组的方法。

```text
${#arrays.toArray(object)}
${#arrays.toStringArray(object)}
${#arrays.toIntegerArray(object)}
${#arrays.toLongArray(object)}
${#arrays.toDoubleArray(object)}
${#arrays.toFloatArray(object)}
${#arrays.toBooleanArray(object)}
${#arrays.length(array)}
${#arrays.isEmpty(array)}
${#arrays.contains(array, element)}
${#arrays.containsAll(array, elements)}
```
# lists: 列表方法。

```text
${#lists.toList(object)}
${#lists.size(list)}
${#lists.isEmpty(list)}
${#lists.contains(list, element)}
${#lists.containsAll(list, elements)}
${#lists.sort(list)}
${#lists.sort(list, comparator)}
```

# sets: 集合的实用方法。

```text
${#sets.toSet(object)}
${#sets.size(set)}
${#sets.isEmpty(set)}
${#sets.contains(set, element)}
${#sets.containsAll(set, elements)}
```

# maps: 地图方法。

```text
${#maps.size(map)}
${#maps.isEmpty(map)}
${#maps.containsKey(map, key)}
${#maps.containsAllKeys(map, keys)}
${#maps.containsValue(map, value)}
${#maps.containsAllValues(map, value)}
```

# aggregates: 在数组或集合上创建聚合的方法。

```text
${#aggregates.sum(array)}
${#aggregates.sum(collection)}
${#aggregates.avg(array)}
${#aggregates.avg(collection)}
```

# ids：处理可能重复的 id 属性的方法（例如，作为迭代的结果）。

```text
${#ids.seq('someId')}
${#ids.next('someId')}
${#ids.prev('someId')}
```

### 常用属性

```text
关键字	            功能介绍	                                           案例
th:id	            替换id	                              <input th:id="'xxx' + ${collect.id}"/>
th:text	            文本替换	                              <p th:text="${collect.description}">description</p>
th:utext	        支持html的文本替换                    	  <p th:utext="${htmlcontent}">conten</p>
th:object	        替换对象	                              <div th:object="${session.user}">
th:value	        属性赋值	                              <input th:value="${user.name}" />
th:with	            变量赋值运算	                          <div th:with="isEven=${prodStat.count}%2==0"></div>
th:style	        设置样式                                <div th:style="'display:' + @{(${sitrue} ? 'none' : 'inline-block')} + ''" ></div>
th:onclick	        点击事件	                              th:onclick="'getCollect()'"
th:each	            迭代	                                  <tr th:each="user,userStat:${users}">
th:if	            判断条件	                              <a th:if="${userId == collect.userId}" >
th:unless	        和th:if判断相反	                      <a th:href="@{/login}" th:unless=${session.user != null}>Login</a>
th:href	            链接地址	                              <a th:href="@{/login}" th:unless=${session.user != null}>Login</a> />
th:switch	        多路选择 配合th:case 使用	              <div th:switch="${user.role}">
th:case	            th:switch的一个分支	                  <p th:case="'admin'">User is an administrator</p>
th:fragment	        布局标签，定义一个代码片段，方便其它地方引用  <div th:fragment="alert">
th:include	        布局标签，替换内容到引入的文件	          <head th:include="layout :: htmlhead" th:with="title='xx'"></head> />
th:replace	        布局标签，替换整个标签到引入的文件	      <div th:replace="fragments/header :: title"></div>
th:selected	        selected选择框 选中	                  th:selected="(${xxx.id} == ${configObj.dd})"
th:src	            图片类地址引入	                          <img class="img-responsive" alt="App Logo" th:src="@{/img/logo.png}" />
th:inline	        内联	                                  有三个取值类型 (text, javascript 和 none)，值为 none 什么都不做，没有效果
内敛文本表达式不依赖于 html 标签，直接使用内敛表达式[[表达式]]即可获取动态数据，但必须要求在父级标签上加 th:inline = “text”属性
<script type="text/javascript" th:inline="javascript">在 js 代码中获取后台的动态数据
th:action	        表单提交的地址	                          <form action="subscribe.html" th:action="@{/subscribe}">
th:remove	        删除某个属性	                          <tr th:remove="all">
1.all:删除包含标签及其所有子标签。
2.body:不删除包含标签,但删除其所有子标签。
3.tag:删除包含标签,但不删除其所有子标签。
4.all-but-first:删除包含标签的所有子标签，除了第一个。
5.none:什么也不做。这个值是有用的动态评估。
th:attr	            设置标签属性，多个属性可以用逗号分隔         比如 th:attr="src=@{/image/aa.jpg},title=#{logo}"，此标签不太优雅，一般用的比较少。

```

### 属性优先级

```text
命令	                   特征	                        属性
1	                 片段包含	                th:insert
th:replace
2	                 片段迭代	                th:each
3	                 条件评估	                th:if
th:unless
th:switch
th:case
4	                局部变量定义	                th:object
th:with
5	                通用属性修改	                th:attr
th:attrprepend
th:attrappend
6	                具体属性修改	                th:value
th:href
th:src
...
7	                文本（标签体修改）	            th:text
th:utext
8	                片段规格	                    th:fragment
9	                碎片去除	                    th:remove

```

### 布局

### HTML5 属性
```text
th:abbr	                th:accept	                th:accept-charset
th:accesskey	        th:action	                th:align
th:alt	                th:archive	                th:audio
th:autocomplete	        th:axis	                    th:background
th:bgcolor	            th:border	                th:cellpadding
th:cellspacing	        th:challenge	            th:charset
th:cite	                th:class	                th:classid
th:codebase	            th:codetype	                th:cols
th:colspan	            th:compact	                th:content
th:contenteditable	    th:contextmenu	            th:data
th:datetime	            th:dir	                    th:draggable
th:dropzone	            th:enctype	                th:for
th:form	                th:formaction	            th:formenctype
th:formmethod	        th:formtarget	            th:fragment
th:frame	            th:frameborder	            th:headers
th:height	            th:high	                    th:href
th:hreflang	            th:hspace	                th:http-equiv
th:icon	                th:id	                    th:inline
th:keytype	            th:kind	                    th:label
th:lang	                th:list                 	th:longdesc
th:low	                th:manifest	                th:marginheight
th:marginwidth	        th:max	                    th:maxlength
th:media	            th:method	                th:min
th:name	                th:onabort	                th:onafterprint
th:onbeforeprint	    th:onbeforeunload	        th:onblur
th:oncanplay	        th:oncanplaythrough     	th:onchange
th:onclick	            th:oncontextmenu        	th:ondblclick
th:ondrag	            th:ondragend	            th:ondragenter
th:ondragleave	        th:ondragover           	th:ondragstart
th:ondrop	            th:ondurationchange     	th:onemptied
th:onended	            th:onerror	                th:onfocus
th:onformchange	        th:onforminput	            th:onhashchange
th:oninput	            th:oninvalid	            th:onkeydown
th:onkeypress	        th:onkeyup	                th:onload
th:onloadeddata	        th:onloadedmetadata	        th:onloadstart
th:onmessage	        th:onmousedown	            th:onmousemove
th:onmouseout	        th:onmouseover          	th:onmouseup
th:onmousewheel	        th:onoffline	            th:ononline
th:onpause	            th:onplay	                th:onplaying
th:onpopstate	        th:onprogress           	th:onratechange
th:onreadystatechange	th:onredo               	th:onreset
th:onresize	            th:onscroll             	th:onseeked
th:onseeking	        th:onselect             	th:onshow
th:onstalled	        th:onstorage            	th:onsubmit
th:onsuspend	        th:ontimeupdate         	th:onundo
th:onunload	            th:onvolumechange	        th:onwaiting
th:optimum	            th:pattern	                th:placeholder
th:poster	            th:preload                  th:radiogroup
th:rel	                th:rev	                    th:rows
th:rowspan	            th:rules	                th:sandbox
th:scheme	            th:scope	                th:scrolling
th:size	                th:sizes	                th:span
th:spellcheck	        th:src	                    th:srclang
th:standby	            th:start	                th:step
th:style	            th:summary              	th:tabindex
th:target	            th:title	                th:type
th:usemap	            th:value	                th:valuetype
th:vspace	            th:width	                th:wrap
th:xmlbase	            th:xmllang	                th:xmlspace
```