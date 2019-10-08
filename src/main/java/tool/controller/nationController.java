package tool.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import pinyin.PinyinUtil;
import tool.bean.WowNpc;
@Controller
public class nationController {
	@RequestMapping("/getPinyin")
	@ResponseBody
	public String getPinyin(HttpServletRequest req) {
		String str = req.getParameter("pystr");
		System.out.println("run into here");
		return PinyinUtil.parseFullHeadPinyin(str);
	}
	
	HashSet<String> hs_name  = new HashSet<>();
	JSONArray nameDict=JSONUtil.createArray() ;
	JSONArray nameDict_token=JSONUtil.createArray() ;
	
	HashSet<String> hs_nation  = new HashSet<>();
	JSONArray nationDict=JSONUtil.createArray() ;
	JSONArray nationDict_token=JSONUtil.createArray() ;
	
	List<WowNpc> lwnpc = new ArrayList<>();
	
	{
		hs_nation.add("中国");hs_name.add("希尔瓦娜斯");
		hs_nation.add("美国");hs_name.add("阿尔萨斯");
		hs_nation.add("巴西");hs_name.add("帕拉丁");
		hs_nation.add("英国");hs_name.add("古尔丹");
		hs_nation.add("澳大利亚");hs_name.add("泰兰德");
		hs_nation.add("加拿大");hs_name.add("艾格文");
		hs_nation.add("柬埔寨");hs_name.add("萨格拉斯");
		hs_nation.add("法国");hs_name.add("瑞恩");
		hs_nation.add("日本");hs_name.add("凯尔萨斯");
		hs_nation.add("印度");hs_name.add("伊利丹");
		hs_nation.add("埃及");hs_name.add("玛维影之歌");
		hs_nation.add("阿根廷");hs_name.add("玛里苟斯");
		hs_nation.add("奥地利");hs_name.add("江户川乱步");
		hs_nation.add("德国");hs_name.add("毛利小无量");
		for (String unit : hs_nation) {
			JSONObject tmp = JSONUtil.createObj();
			tmp.put("value", unit);
			tmp.put("text", unit);
			nationDict.add(tmp);
		}
		for (String unit : hs_nation) {
			JSONObject tmp = JSONUtil.createObj();
			tmp.put("token", PinyinUtil.parseFullHeadPinyin(unit));
			tmp.put("text", unit);
			nationDict_token.add(tmp);
		}
		for (String unit : hs_name) {
			JSONObject tmp = JSONUtil.createObj();
			tmp.put("value", unit);
			tmp.put("text", unit);
			nameDict.add(tmp);
		}
		for (String unit : hs_name) {
			JSONObject tmp = JSONUtil.createObj();
			tmp.put("token", PinyinUtil.parseFullHeadPinyin(unit));
			tmp.put("text", unit);
			nameDict_token.add(tmp);
		}
		int auto_inc = 1;
		for(String nation : hs_nation) {
			for(String name : hs_name) {
				WowNpc tmp = new WowNpc();
				tmp.id= auto_inc;
				tmp.name=name;
				tmp.zhenying = nation;
				tmp.tianfu = "毁灭";
				tmp.wuqi = "阿古斯";
				tmp.zhiye = "术士";
				lwnpc.add(tmp);
				auto_inc ++;
			}
		}
		hs_nation.forEach(n->System.out.println(n));
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
	@RequestMapping("/nationDict_token")
	@ResponseBody
	public JSONArray nationDict_token(HttpServletRequest req) {
		String search_key = req.getParameter("search_key");
		System.out.println(search_key);
		if(search_key != null) {
			if(search_key.equals("")) {
				return nationDict_token;
			}else {
				JSONArray res = JSONUtil.createArray();
				for (Object o : nationDict) {
					JSONObject oo = (JSONObject) o ; 
					System.out.println(oo.get("value")  + " :  " + search_key);
					if(oo.getBool("value") !=null) {
						boolean inc1 = oo.get("value").toString().indexOf(search_key) != -1 ;
						boolean inc2 = oo.get("token").toString().indexOf(search_key) != -1;
						if(inc1 || inc2) {
							res.add(oo);
						}
					}
				}
				return	res;
			}
		}else {
			return nationDict_token;
		}
	}
	
	@RequestMapping("/nameDict")
	@ResponseBody
	public JSONArray nameDict(HttpServletRequest req) {
		String search_key = req.getParameter("search_key");
		System.out.println(search_key);
		System.out.println(nameDict.size());
		if(search_key != null) {
			if(search_key.equals("")) {
				return nameDict;
			}else {
				JSONArray res = JSONUtil.createArray();
				for (Object o : nameDict) {
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
			return nameDict;
		}
	}
	
	@RequestMapping("/nameDict_token")
	@ResponseBody
	public JSONArray nameDict_token(HttpServletRequest req) {
		String search_key = req.getParameter("search_key");
		System.out.println(search_key);
		if(search_key != null) {
			if(search_key.equals("")) {
				return nameDict_token;
			}else {
				JSONArray res = JSONUtil.createArray();
				for (Object o : nameDict) {
					JSONObject oo = (JSONObject) o ; 
					System.out.println(oo.get("value")  + " :  " + search_key);
					if(oo.getBool("value") !=null) {
						boolean inc1 = oo.get("value").toString().indexOf(search_key) != -1 ;
						boolean inc2 = oo.get("token").toString().indexOf(search_key) != -1;
						if(inc1 || inc2) {
							res.add(oo);
						}
					}
				}
				return	res;
			}
		}else {
			return nameDict_token;
		}
	}
	@RequestMapping("/nn_data")
	@ResponseBody
	public JSONArray nn_data(HttpServletRequest req) {
		List<WowNpc> result = new ArrayList<>();
		String srhKey = req.getParameter("srhKey");
		if(Strings.isBlank(srhKey) || srhKey.equals("_")) {
			//此分支返回所有数据的前10个
			return JSONUtil.parseArray(lwnpc.subList(0, 4)) ;
		}else if(! srhKey.equals("_") && srhKey.startsWith("_")){
			//此分支返回根据名称搜索的结果
			for(WowNpc  t : lwnpc) {
				if(t.name.equals(srhKey.replaceAll("_", ""))){
					result.add(t);
				}
			}
			return JSONUtil.parseArray(result);
		}else if(! srhKey.equals("_") && srhKey.endsWith("_")) {
			//此分支返回根据国家搜索的结果
			for(WowNpc  t : lwnpc) {
				if(t.zhenying.equals(srhKey.replaceAll("_", ""))){
					result.add(t);
				}
			}
			return JSONUtil.parseArray(result);
			
		}else {
			//此分支返回根据国家+名称搜索的结果
			String[] ks = srhKey.split("_");
			for(WowNpc  t : lwnpc) {
				if(t.zhenying.equals(ks[0]) &&  t.name.equals(ks[1])){
					result.add(t);
				}
			}
			return JSONUtil.parseArray(result);
		}
	}
}
