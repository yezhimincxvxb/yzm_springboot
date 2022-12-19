# 简单配置
```text
logging:
  level:
    root: INFO
    com.com.com.yzm.logback.controller: ERROR
    com.com.com.yzm.logback.service: WARN
    com.com.com.yzm.logback.mapper: DEBUG
  file:
    name: C:\log\demo.log
#  file:
#    path: C:\log
```
file.name 和 file.path 只能二选一，file.path生成的日志文件为spring.log(文件名是固定的，不能更改)；file.name创建demo.log(文件名是可以更改的)
logging.path和logging.file的value都可以是相对路径或者绝对路径

# 复杂配置
```text
logging:
  # 文件名默认是logback，可以不用配置
  config: classpath:logback.xml
```