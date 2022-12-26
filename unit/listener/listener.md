# 监听Context、Session、Request对象的创建及销毁

# 自定义监听事件
```text
1、定义事件：
MyApplicationEvent 继承 ApplicationEvent

2、监听事件：
MyApplicationListener 实现 ApplicationListener

3、事件发布：
MyPublisher

4、事件只有发布了才会被监听，即每次监听事件都需要重新发布一下
```
