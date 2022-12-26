package com.yzm.submit.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzm.submit.anno.Submit2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;


@Aspect
@Configuration
public class SubmitAspect {

    //redis缓存
//    @Autowired
//    private RedisUtils redisUtils;

//    @Around("@annotation(com.yzm.submit.submit.Submit)")
//    public Object interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
//        // 如果提交参数为空，则放行
//        Object[] args = joinPoint.getArgs();
//        if (args.length <= 0) return joinPoint.proceed();
//
//        // 获取注解信息
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        Submit submit = method.getAnnotation(Submit.class);
//        String prefix = submit.prefix();
//        int delaySeconds = submit.delaySeconds();
//
//        // 判断缓存是否已存在key，存在则为重复请求
//        String key = prefix + JSON.toJSONString(args);
//
//        if (redisUtils.exists(key)) {
//            throw new RuntimeException("请勿重复请求");
//        }
//
//        String value = String.valueOf(System.currentTimeMillis());
//        //存储key:value并设置过期时间
//        redisUtils.setex(key, value, delaySeconds);
//        return joinPoint.proceed();
//    }

    @Around("@annotation(com.yzm.submit.anno.Submit2)")
    public Object handleResubmit(ProceedingJoinPoint joinPoint) throws Throwable {
        // 如果提交参数为空，则放行
        Object[] args = joinPoint.getArgs();
        if (args.length <= 0) return joinPoint.proceed();

        // 获取注解信息
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Submit2 annotation = method.getAnnotation(Submit2.class);
        int delaySeconds = annotation.delaySeconds();

        String key = new ObjectMapper().writeValueAsString(args);
        String value = String.valueOf(System.currentTimeMillis());

        // 执行锁
        boolean lock = false;
        try {
            // 是否已存在key
            lock = SubmitLock.getInstance().lock(key, value);
            if (lock) {
                throw new RuntimeException("请勿重复提交");
            }
            return joinPoint.proceed();
        } finally {
            // 设置解锁key和解锁时间
            SubmitLock.getInstance().unLock(lock, key, delaySeconds);
        }
    }
}

