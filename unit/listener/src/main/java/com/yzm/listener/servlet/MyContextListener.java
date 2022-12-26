package com.yzm.listener.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ServletContextListener -- 监听servletContext对象的创建以及销毁
 * 程序启动时创建，程序关闭时销毁。只创建一次
 * 用来初始化数据，用于缓存
 */
@Slf4j
@Component
public class MyContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("initData","初始化数据");
        log.info("ServletContext 创建");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("ServletContext 销毁");
    }
}
