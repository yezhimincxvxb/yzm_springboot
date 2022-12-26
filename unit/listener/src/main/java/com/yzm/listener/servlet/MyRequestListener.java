package com.yzm.listener.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * ServletRequestListener -- 监听request对象的创建以及销毁
 * 请求时创建，响应后销毁。每次请求都会创建
 */
@Slf4j
@Component
public class MyRequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        request.setAttribute("token","123456");
        log.info("session id为：{}", request.getRequestedSessionId());
        log.info("request url为：{}", request.getRequestURL());
        log.info("ServletRequest 创建");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log.info("ServletRequest 销毁");
    }
}
