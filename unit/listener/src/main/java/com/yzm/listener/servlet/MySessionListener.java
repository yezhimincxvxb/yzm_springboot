package com.yzm.listener.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * HttpSessionListener -- 监听HttpSession对象的创建以及销毁
 * request.getSession时创建，通过设置过期时间销毁session.setMaxInactiveInterval(10);单位：秒(不设置也有默认过期时间)
 */
@Slf4j
@Component
public class MySessionListener implements HttpSessionListener {

    //在线人数
    private final AtomicInteger count = new AtomicInteger(1);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("session 创建");
        HttpSession session = se.getSession();
        session.setAttribute("activeUser", count.getAndIncrement());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("session 销毁");
        HttpSession session = se.getSession();
        session.setAttribute("activeUser", count.getAndDecrement());
    }
}
