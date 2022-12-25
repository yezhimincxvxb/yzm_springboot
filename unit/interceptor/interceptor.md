### addInterceptors：拦截器
```text
addInterceptor：需要一个实现HandlerInterceptor接口的拦截器实例
addPathPatterns：用于设置拦截器的过滤路径规则；addPathPatterns("/**")对所有请求都拦截
excludePathPatterns：用于设置不需要拦截的过滤规则
拦截器主要用途：进行用户登录状态的拦截，日志的拦截等。
@Override
public void addInterceptors(InterceptorRegistry registry) {
super.addInterceptors(registry);
registry.addInterceptor(new TestInterceptor())
.addPathPatterns("/**")
.excludePathPatterns("/emp/toLogin","/emp/login","/js/**","/css/**","/images/**");
}
```
### addViewControllers：页面跳转
```text
以前写SpringMVC的时候，如果需要访问一个页面，必须要写Controller类，然后再写一个方法跳转到页面，感觉好麻烦，其实重写WebMvcConfigurer中的addViewControllers方法即可达到效果了
@Override
public void addViewControllers(ViewControllerRegistry registry) {
registry.addViewController("/toLogin").setViewName("login");
}
值的指出的是，在这里重写addViewControllers方法，并不会覆盖WebMvcAutoConfiguration（Springboot自动配置）中的addViewControllers（在此方法中，Spring Boot将“/”映射至index.html），
这也就意味着自己的配置和Spring Boot的自动配置同时有效，这也是我们推荐添加自己的MVC配置的方式。
```
### addResourceHandlers：静态资源
```text
比如，我们想自定义静态资源映射目录的话，只需重写addResourceHandlers方法即可。
addResourceHandler：指的是对外暴露的访问路径
addResourceLocations：指的是内部文件放置的目录
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
registry.addResourceHandler("swagger-ui.html")
.addResourceLocations("classpath:/META-INF/resources/");
registry.addResourceHandler("/webjars/**")
.addResourceLocations("classpath:/META-INF/resources/webjars/");
}
```
### configureDefaultServletHandling：默认静态资源处理器
```text
@Override
public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
configurer.enable();
configurer.enable("defaultServletName");
}
此时会注册一个默认的Handler：DefaultServletHttpRequestHandler，这个Handler也是用来处理静态文件的，它会尝试映射/。
当DispatcherServlet映射/时（/ 和/ 是有区别的），并且没有找到合适的Handler来处理请求时，就会交给DefaultServletHttpRequestHandler 来处理。
注意：这里的静态资源是放置在web根目录下，而非WEB-INF 下。
可能这里的描述有点不好懂（我自己也这么觉得），
所以简单举个例子，例如：在webroot目录下有一个图片：1.png 我们知道Servlet规范中web根目录（webroot）下的文件可以直接访问的，但是由于DispatcherServlet配置了映射路径是：/ ，
它几乎把所有的请求都拦截了，从而导致1.png 访问不到，这时注册一个DefaultServletHttpRequestHandler 就可以解决这个问题。
其实可以理解为DispatcherServlet破坏了Servlet的一个特性（根目录下的文件可以直接访问），DefaultServletHttpRequestHandler是帮助回归这个特性的。
```
### configureViewResolvers：视图解析器
```text
这个方法是用来配置视图解析器的，该方法的参数ViewResolverRegistry 是一个注册器，用来注册你想自定义的视图解析器等。
@Bean
public InternalResourceViewResolver resourceViewResolver() {
InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//请求视图文件的前缀地址
internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
//请求视图文件的后缀
internalResourceViewResolver.setSuffix(".jsp");
return internalResourceViewResolver;
}

@Override
public void configureViewResolvers(ViewResolverRegistry registry) {
super.configureViewResolvers(registry);
registry.viewResolver(resourceViewResolver());
/*registry.jsp("/WEB-INF/jsp/",".jsp");*/
}
```
### ddCorsMappings：跨域
```text
@Override
public void addCorsMappings(CorsRegistry registry) {
super.addCorsMappings(registry);
registry.addMapping("/cors/**") // 允许跨域访问的路径
.allowedOrigins("*")  // 允许跨域访问的源
.allowedHeaders("*")  // 允许头部设置
.allowedMethods("POST","GET")  // 允许请求方法
.maxAge(168000);    // 预检间隔时间
}
```