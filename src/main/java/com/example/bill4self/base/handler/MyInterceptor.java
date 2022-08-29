package com.example.bill4self.base.handler;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author Josh-ZJUT
 * @date 2022/8/29 15:16
 * @email dujianghui537885@163.com
 */
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String requestUri = request.getRequestURI();
        // /account/list
        String substring = requestUri.substring(1);
        // account/list
        final int index = substring.indexOf("/");
        if (index != -1) {
            substring = substring.substring(0, index);
        }
        // account
        Set<String> urls = (Set<String>) request.getSession().getAttribute("module");
        boolean pass = false;
        if (urls != null) {
            pass = urls.stream().anyMatch(substring::equals);
        }
        if (!pass) {
            response.sendRedirect("/");
        }
        return pass;
    }
}
