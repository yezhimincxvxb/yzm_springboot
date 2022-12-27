package com.yzm.locker.aspect;

import com.yzm.locker.anno.CurLockerAnno;
import com.yzm.locker.anno.CurLockerFieldAnno;
import com.yzm.locker.anno.CurLockerType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Component
@Slf4j
@Order(101)
public class CurLockerAspect {

    //    @Autowired
//    RedisTemplate<String, Object> redisTemplate;
    private static final ConcurrentHashMap<String, Object> currentMap = new ConcurrentHashMap<>();


    private final static String DELIMITER = "-";

    @Around("@annotation(concurrentLocker)")
    public Object around(ProceedingJoinPoint pjp, CurLockerAnno concurrentLocker) throws Throwable {
        Object[] args = pjp.getArgs();
        //开启分布式锁控制
        try {
            String suffixKey;
            //使用EL表达式
            if (CurLockerType.EL.equals(concurrentLocker.expressionType())) {
                Map<String, Object> paramMap = getRequestParams(pjp);
                String el = concurrentLocker.expression();
                if (!el.contains("#{")) {
                    el = "#{" + el + "}";
                }
                suffixKey = getValue(el, paramMap);
            } else {
                //普通处理方法
                Signature signature = pjp.getSignature();
                MethodSignature methodSignature = (MethodSignature) signature;
                Method targetMethod = methodSignature.getMethod();
                //获得方法中参数的注解
                Annotation[][] annotations = targetMethod.getParameterAnnotations();
                List<ImmutablePair<CurLockerFieldAnno, String>> list = new ArrayList<>();
                for (int i = 0; i < annotations.length; i++) {
                    Optional<Annotation> optional = Stream.of(annotations[i]).filter(x -> x instanceof CurLockerFieldAnno).findAny();
                    if (optional.isPresent()) {
                        Class<?> ageClass = args[i].getClass();
                        CurLockerFieldAnno annotation = (CurLockerFieldAnno) optional.get();
                        ArrayList<String> paras = new ArrayList<>();
                        for (String fieldString : annotation.field()) {
                            Field field = ageClass.getDeclaredField(fieldString);
                            field.setAccessible(true);
                            paras.add(String.valueOf(field.get(args[i])));
                        }
                        list.add(ImmutablePair.of(annotation, String.join(DELIMITER, paras)));
                    }
                }
                suffixKey = list.stream().map(x -> ImmutablePair.of(x.left.order(), x.right)).sorted(Comparator.comparing(ImmutablePair::getLeft)).map(ImmutablePair::getRight).collect(Collectors.joining(DELIMITER));
            }

            String key = concurrentLocker.lockPreName() + DELIMITER + suffixKey;
            if (lock(key, Duration.ofMillis(concurrentLocker.expireTime()))) {
                // 临界资源
                try {
                    return pjp.proceed(args);
                } finally {
                    Boolean result = unlock(key);
                    log.info("释放锁{}, {}", key, result);
                }
            }
        } catch (Exception e) {
            log.error("处理失败", e);
            throw new RuntimeException("系统处理失败");
        }

        throw new RuntimeException("请稍等片刻");
    }

    private Boolean lock(String key, Duration duration) {
        return Objects.isNull(currentMap.putIfAbsent(key, duration));
//        return redisTemplate.opsForValue().setIfAbsent(key, key, duration);
    }

    private Boolean unlock(String key) {
        return Objects.isNull(currentMap.remove(key));
//        return redisTemplate.delete(key);
    }

    private Map<String, Object> getRequestParams(ProceedingJoinPoint pjp) {
        Map<String, Object> allParams = new HashMap<>();
        Object[] paramValues = pjp.getArgs();
        String[] paramNames = ((CodeSignature) pjp.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            allParams.put(paramNames[i], paramValues[i]);
            allParams.put("p" + i, paramValues[i]);
        }
        return allParams;
    }

    public String getValue(String el, Map<String, Object> paramMap) {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        for (Map.Entry<String, Object> param : paramMap.entrySet()) {
            context.setVariable(param.getKey(), param.getValue());
        }
        return parser.parseExpression(el, new TemplateParserContext()).getValue(context, String.class);
    }
}
