package com.yzm.aop.config;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 请求日志记录处理
 */
@Aspect
@Component
public class RequestLogAspect {
    private static final Logger log = LoggerFactory.getLogger(RequestLogAspect.class);

    private static final Set<String> includeHeaderParam = new HashSet<>();
    private static final Set<String> excludeUrl = new HashSet<>();

    static {
        includeHeaderParam.add("shopCode");
        includeHeaderParam.add("Authorization");
        excludeUrl.add("/v2/api-docs");
        excludeUrl.add("/error");
        excludeUrl.add("/favicon.ico");
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Controller) ")
    public void requestLog() {
    }

    @Around(value = "requestLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        if (excludeUrl.contains(request.getRequestURI())) {
            return joinPoint.proceed();
        }

        try {
            HttpServletResponse response = attributes.getResponse();
            String uuid = IdUtil.simpleUUID();

            log.info("请求ID：{}", uuid);
            log.info("请求URL : {}", request.getRequestURL().toString());
            log.info("请求方式 : {}", request.getMethod());
//            log.info("请求IP地址 : {}", IpUtils.getIpAddr(request));
            log.info("请求类方法 : {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            log.info("请求头参数: {}", JSON.toJSONString(getHeaderParams(request), true));
            log.info("请求参数: {}", JSON.toJSONString(joinPoint.getArgs(), true));

            Object proceed = joinPoint.proceed();

            log.info("请求ID：{}", uuid);
            log.info("请求耗时：{} ms", System.currentTimeMillis() - startTime);
            log.info("响应状态：{}", response.getStatus());

            JSONObject parse = handleResult(proceed);
            String jsonString = JSON.toJSONString(parse, true);
            if (jsonString.length() > 400) {
                String concat = jsonString.substring(0, 400).concat("\r\n").concat("...}");
                log.info("响应结果：{}", concat);
            } else {
                log.info("响应结果：{}", jsonString);
            }

            return proceed;
        } catch (Exception e) {
        }

        return joinPoint.proceed();
    }

    private Map<String, Object> getHeaderParams(HttpServletRequest request) {
        Map<String, Object> headerParams = new HashMap<>();
        for (String s : includeHeaderParam) {
            headerParams.put(s, request.getHeader(s));
        }
        return headerParams;
    }

    private JSONObject handleResult(Object obj) {
        if (Objects.isNull(obj)) {
            return new JSONObject();
        }
        return simpleData(obj);
    }

    private JSONObject simpleData(Object obj) {
        JSONObject parse = null;
        try {
            parse = JSONObject.parseObject(JSON.toJSONString(obj));
        } catch (JSONException e) {
            return parse;
        }
        if (parse.containsKey("data")) {
            Object data = parse.get("data");
            if (ArrayUtil.isArray(data)) {
                Object[] ds = (Object[]) data;
                if (ds.length > 1) parse.put("data", Collections.singleton(ds[0]));
            } else if (data instanceof List) {
                List list = (List) data;
                if (list.size() > 1) parse.put("data", Collections.singleton(list.get(0)));
            } else {
                JSONObject simpleData = simpleData(data);
                if (Objects.nonNull(simpleData)) parse.put("data", simpleData);
            }
        }
        return parse;
    }


}
