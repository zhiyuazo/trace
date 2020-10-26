package com.zzy.trace.loginAuth;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Logout {
	@RequestMapping("/logout")
	@ResponseBody
    public void logout(HttpSession session) {
        session.removeAttribute("user");
    }
}
