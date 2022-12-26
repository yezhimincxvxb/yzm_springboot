package com.yzm.listener.controller;

import com.yzm.listener.spring.MyPublisher;
import com.yzm.listener.spring.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/listener")
public class ListenerController {

    @GetMapping("/context")
    public Object context(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        return servletContext.getAttribute("initData");
    }

    @GetMapping("/context2")
    public Object context2(HttpServletRequest request) {
        try {
            ServletContext context = request.getServletContext();
            context.setAttribute("context_name", "context_value");
            Thread.sleep(5000);
            context.setAttribute("context_name", "context_value_again");
            Thread.sleep(5000);
            context.removeAttribute("context_name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "context 属性变化";
    }

    @GetMapping("/session")
    public Object session(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie;
        try {
            // 把sessionId记录在浏览器中
            cookie = new Cookie("JSESSIONID", URLEncoder.encode(request.getSession().getId(), "utf-8"));
            cookie.setPath("/");
            //设置cookie有效期为2天，设置长一点
            cookie.setMaxAge(48 * 60 * 60);
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        return session.getAttribute("activeUser");
    }

    @GetMapping("/session2")
    public Object session2(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            session.setAttribute("session_name", "session_value");
            Thread.sleep(5000);
            session.setAttribute("session_name", "session_value_again");
            Thread.sleep(5000);
            session.removeAttribute("session_name");
            session.setMaxInactiveInterval(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "session 属性变化";
    }

    @GetMapping("/request")
    public Object request(HttpServletRequest request) {
        return request.getAttribute("token");
    }

    @GetMapping("/request2")
    public Object request2(HttpServletRequest request) {
        try {
            request.setAttribute("request_name", "request_value");
            Thread.sleep(5000);
            request.setAttribute("request_name", "request_value_again");
            Thread.sleep(5000);
            request.removeAttribute("request_name");
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "request 属性变化";
    }

    @Autowired
    private MyPublisher myPublisher;

    /**
     * 事件触发
     */
    @GetMapping("/publisher")
    public Object publisher() {
        myPublisher.publish(new Task("张三", "木匠"));
        return "success";
    }

}
