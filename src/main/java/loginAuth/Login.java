package loginAuth;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Login {
	@RequestMapping("/login")
	@ResponseBody
    public void login(String user , String password,HttpSession session,HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest) throws IOException {
    	User  u = new User();
    	u.setName(user);
    	u.setPassword(password);
        session.setAttribute("user", u);
        
        String originalUrl = httpServletRequest.getRequestURI();
        System.out.println("original URL= " + originalUrl);
        httpServletResponse.sendRedirect("hello");
    }
}
