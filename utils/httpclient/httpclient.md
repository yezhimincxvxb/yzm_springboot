### getForObject && getForEntity
```text
getForEntity与getForObject最大的区别就在于返回内容不一样：
getForEntity返回的是一个ResponseEntity，而getForObject返回的就只是返回内容。
getForObject的返回相当于只返回http的body部份而getForEntity的返回是返回全部信息
一般来说一是取status判断请求是否成功，成功则去取body里面的内容。
```
###
```text
请求参数中常用的url一般都是String，所以以url参数类型是URI的可以不管，用的少。
可变参数Object... uriVariables和Map<String, ?>
uriVariables里面传的参数都是url上的参数，本质上没有什么区别，重点是url上一定要占位。
请求参数Class<T> responseType和ParameterizedTypeReference<T>
responseType都是用来指定返回数据的类型的，对于简单类型的用Class<T>就可以了，但是如果返回类型太过复杂（如对象嵌套之类的）可以用ParameterizedTypeReference<T>来指定返回类型。
关于返回，XXXForObject的方法都只返回具体内容，不返回Http的头信息和状态信息，要返回头信息和状态信息的时候就要使用XXXForEntity，这一类方法都是返回ResponseEntity<T>。
exchange方法可以发多种类型的Http请求，本质上与具体的某一类的请求没有太大差别，唯一的是exchange方法中有可以参数化响应类型的请求参数：ParameterizedTypeReference<T>，在特定的时候比较合用。

```
