package com.zzy.trace.loginAuth;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        String contextPath=session.getServletContext().getContextPath();
        String[] requireAuthPages = new String[]{"home.html"};
  
        String uri = httpServletRequest.getRequestURI();
 
        String[] sub_uri = StringUtils.split(uri,"/");
        String page = sub_uri[sub_uri.length -1];
        System.out.println(String.format("req-uri=%s final-page=%s", uri,page));
         
        if(begingWith(page, requireAuthPages)){
            User u =  (User)session.getAttribute("user");
            System.out.println("@checkAuth=: " +u);
            if(u==null) {
                httpServletResponse.sendRedirect("/login/login.html");
                return false;
            }
        }
        return true;  
    }
 
    private boolean begingWith(String page, String[] requiredAuthPages) {
        boolean result = false;
        for (String requiredAuthPage : requiredAuthPages) {
            if(StringUtils.startsWith(page, requiredAuthPage)) {
                result = true; 
                break;
            }
        }
        return result;
    }
 
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
 
    }
 
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}