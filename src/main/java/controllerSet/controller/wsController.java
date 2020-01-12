package controllerSet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class wsController {
	@RequestMapping("/ws")
	@ResponseBody
	public String ws() { 
		return "????";
	} 
}
