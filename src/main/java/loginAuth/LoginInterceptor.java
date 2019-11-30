package loginAuth;

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
        String[] requireAuthPages = new String[]{"test/index.html","hello"};
  
        String uri = httpServletRequest.getRequestURI();
        System.out.println(uri);
 
        Enumeration<String> res = session.getAttributeNames();
        if(res.hasMoreElements()) {
        	String key = res.nextElement();
        	System.out.println(key+ ":" + session.getValue(key));
        }
        uri = StringUtils.remove(uri, contextPath+"/");
        String page = uri;
         
        if(begingWith(page, requireAuthPages)){
            Object authflag =  session.getAttribute("user");
            System.out.println("authflag=: " +authflag);
            if(authflag==null) {
                httpServletResponse.sendRedirect("login/login.html");
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