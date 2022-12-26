### 概述
接口文档在线自动生成，文档随接口变动实时更新，节省维护成本
支持在线接口测试，不依赖第三方工具

### 依赖
```xml
<!-- Swagger -->
<dependency>
    <groupId>com.spring4all</groupId>
    <artifactId>swagger-spring-boot-starter</artifactId>
    <version>1.9.1.RELEASE</version>
</dependency>
或
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
<!-- 整合swagger报guava错时，引入该依赖解决问题 -->
<dependency>
    <groupId>com.google.guava</groupId>
    <artifactId>guava</artifactId>
    <version>28.0-jre</version>
</dependency>
```
### 常用注解
```text
@Api：用在请求的类上，表示对类的说明
value：该参数没什么意义，在UI界面上也看不到，所以不需要配置
tags：说明该类的作用，可以在UI界面上看到的注解(如果设置该值，value就会被覆盖)

@ApiOperation：用在请求的方法上，说明方法的用途、作用
value：说明方法的用途、作用
notes：方法的备注说明

@ApiParam：用在方法参数上；表示对参数进行说明(推荐用@ApiImplicitParam)
name：参数名
value：参数说明
required：是否必填
type：参数类型，String、Long等
defaultValue：默认值
example：参数举例

@ApiImplicitParams：用在请求的方法上，包含一组参数说明
@ApiImplicitParam：用在@ApiImplicitParams中, 对单个参数的说明
name：参数名
value：参数的说明、描述
required：参数是否必须必填
paramType：参数放在哪个地方
· query --> 请求参数的获取：@RequestParam
· header --> 请求参数的获取：@RequestHeader
· path（用于restful接口）--> 请求参数的获取：@PathVariable
· body（请求体）-->  @RequestBody User user
· form（普通表单提交）
dataType：参数类型，默认String，其它值dataType="Integer"
defaultValue：参数的默认值
example：举例说明

@ApiResponses：用在请求的方法上，表示一组响应
@ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
code：数字，例如400
message：信息，例如"请求参数没填好"
response：抛出异常的类

@ApiModel：用于响应类上，表示一个返回响应数据的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）
属性名称	            数据类型	            默认值	            说明
value	            String	            类名	                为模型提供备用名称
description	        String	            “”	                提供详细的类描述
parent	            Class<?> parent	    Void.class	        为模型提供父类以允许描述继承关系
    discriminatory	    String	            “”	                支持模型继承和多态，使用鉴别器的字段的名称，可以断言需要使用哪个子类型
    subTypes	        Class<?>[]	        {}	                从此模型继承的子类型数组
reference	        String	            “”	                指定对应类型定义的引用，覆盖指定的任何其他元数据

@ApiModelProperty：用在属性上，描述响应类的属性
属性名称	            数据类型	            默认值	            说明
value	            String	            “”	                属性简要说明
name	            String	            “”	                运行覆盖属性的名称。重写属性名称
allowableValues	    String	            “”	                限制参数可接收的值，三种方法，固定取值，固定范围
access	            String	            “”	                过滤属性，参阅:io.swagger.core.filter.SwaggerSpecFilter
notes	            String	            “”	                目前尚未使用
dataType	        String	            “”	                参数的数据类型，可以是类名或原始数据类型，此值将覆盖从类属性读取的数据类型
required	        boolean	            false	            是否为必传参数,false:非必传参数; true:必传参数
position	        int	                0	                允许在模型中显示排序属性
hidden	            boolean	            false	            隐藏模型属性，false:不隐藏; true:隐藏
example	            String	            “”	                属性的示例值
readOnly	        boolean	            false	            指定模型属性为只读，false:非只读; true:只读
reference	        String	            “”	                指定对应类型定义的引用，覆盖指定的任何其他元数据
allowEmptyValue	    boolean	            false	            允许传空值，false:不允许传空值; true:允许传空值

@ApiIgnore()用于类或者方法上，可以不被swagger显示在页面上
```
### 测试SwaggerController

### 浏览器上访问：localhost:端口号/swagger-ui.html
===================================================================================================================
### 设置swagger访问页面权限
##需要添加依赖
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>swagger-bootstrap-ui</artifactId>
    <version>1.9.6</version>
</dependency>
##添加@EnableSwaggerBootstrapUI注解
@Configuration
@EnableSwagger2 // 启动swagger
@EnableSwaggerBootstrapUI
public class SwaggerConfig {
##
swagger:
  #线上环境设置为true屏蔽swagger文档功能
  production: false
  #设置访问页面时先登录
  basic:
    enable: true
    username: 12345
    password: 12345































