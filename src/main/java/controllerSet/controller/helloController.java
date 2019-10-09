package controllerSet.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

@Controller
public class helloController {
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello Sprng...";
	}
	@RequestMapping("/json")
	@ResponseBody
	public JSONArray json() {
		List<String> src = new ArrayList<>();
		src.add("a");
		src.add("b");
		src.add("c");
		src.add("d");
		src.add("ee");
		
		JSONArray  res = JSONUtil.parseArray(src);
		return res;
	}
	
}
