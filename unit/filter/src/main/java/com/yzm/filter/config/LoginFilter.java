package com.yzm.filter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(getClass());

    // 配置白名单、放行
    private final List<Pattern> patterns = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化：" + filterConfig.getFilterName());
        patterns.add(Pattern.compile("/filter/index"));
        patterns.add(Pattern.compile("/filter/login"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (isInclude(request.getRequestURI())) {
            log.info("filter 《==》 白名单，放行访问");
            chain.doFilter(request, response);
            return;
        }

        if ("LOGIN".equals(request.getSession().getAttribute("USER"))) {
            log.info("filter 《==》 已登录，放行访问");
            chain.doFilter(request, response);
            return;
        }

        log.info("filter 《==》 未登录，终止操作");
        response.getWriter().write("no_login");
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }

    //判断当前请求是否在白名单
    private boolean isInclude(String url) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}
