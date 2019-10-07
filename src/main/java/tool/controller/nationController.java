package tool.controller;

import java.util.HashSet;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import util.PinyinUtil;
@Controller
public class nationController {
	@RequestMapping("/getPinyin")
	@ResponseBody
	public String getPinyin(HttpServletRequest req) {
		String str = req.getParameter("pystr");
		System.out.println("run into here");
		return PinyinUtil.parseFullHeadPinyin(str);
	}
	
	HashSet<String> hs_nation  = new HashSet<>();
	JSONArray nationDict=JSONUtil.createArray() ;
	{
		hs_nation.add("中国");
		hs_nation.add("美国");
		hs_nation.add("巴西");
		hs_nation.add("英国");
		hs_nation.add("澳大利亚");
		hs_nation.add("加拿大");
		hs_nation.add("柬埔寨");
		hs_nation.add("法国");
		hs_nation.add("日本");
		hs_nation.add("印度");
		hs_nation.add("埃及");
		hs_nation.add("阿根廷");
		hs_nation.add("奥地利");
		hs_nation.add("德国");
		for (String unit : hs_nation) {
			JSONObject tmp = JSONUtil.createObj();
			tmp.put("value", unit);
			tmp.put("text", unit);
			nationDict.add(tmp);
		}
	}
	@RequestMapping("/nationDict")
	@ResponseBody
	public JSONArray nationDict(HttpServletRequest req) {
		String search_key = req.getParameter("search_key");
		System.out.println(search_key);
		if(search_key != null) {
			if(search_key.equals("")) {
				return nationDict;
			}else {
				JSONArray res = JSONUtil.createArray();
				for (Object o : nationDict) {
					JSONObject oo = (JSONObject) o ; 
					System.out.println(oo.get("value")  + " :  " + search_key);
					if(oo.getBool("value") !=null) {
						boolean inc1 = oo.get("value").toString().indexOf(search_key) != -1 ;
						boolean inc2 = PinyinUtil.parseFullHeadPinyin(oo.get("value").toString()).indexOf(search_key) != -1;
						if(inc1 || inc2) {
							res.add(oo);
						}
					}
				}
				return	res;
			}
		}else {
			return nationDict;
		}
	}
}
