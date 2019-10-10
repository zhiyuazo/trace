package controllerSet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import controllerSet.bean.tmplBean;

@Controller
public class tmplController {

	@RequestMapping("/ltmpl")
	@ResponseBody
	public JSONArray tmpl_list() {
		List<tmplBean> ltb = new ArrayList<>();
		for(int i = 0 ; i < 20 ; i ++ ) {
		  tmplBean tb = new tmplBean();
		  tb.id = i;
		  tb.name = RandomUtil.randomString(5);
		  tb.labelt = RandomUtil.randomString(4);
		  tb.owner = RandomUtil.randomString(3);
		  tb.group = RandomUtil.randomString(2);
		  tb.nanyi = (RandomUtil.randomInt(20)>10?false:true)?"难":"易";
		  ltb.add(tb);
		}
		return JSONUtil.parseArray(ltb);
	} 
}
