package	com.zzy.trace.controller ;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzy.trace.bean.Devil;
import com.zzy.trace.bean.Hero;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;	

@Controller
public class tableController {
	
	public ArrayList<Hero> lhero;
	public ArrayList<Devil> ldevil;
	HashSet<String> hs_nation  = new HashSet<>();JSONObject nationDict=JSONUtil.createObj() ;
	HashSet<String> hs_pos  = new HashSet<>(); JSONObject posDict =JSONUtil.createObj();
	HashSet<String> hs_cate  = new HashSet<>();JSONObject cateDict=JSONUtil.createObj() ;
	HashSet<String> hs_kind  = new HashSet<>();JSONObject kindDict=JSONUtil.createObj() ;
	HashSet<String> hs_prop  = new HashSet<>();JSONObject propDict=JSONUtil.createObj() ;
	
	{
		lhero = new ArrayList<>();
		long s = System.currentTimeMillis();
		System.out.println();
		for (int i = 0; i < 200; i++) {
			lhero.add(new Hero(i+1));
		}
		ldevil= new ArrayList<>();
		for (int i = 0; i < 400; i++) {
			Devil tmp = new Devil(i+1);
			tmp.setRef2hero(Math.round(((i+3)/3)));
			ldevil.add(tmp);
		}
		
		for (Hero h : lhero) {
			hs_nation.add(h.getheroNation());
			hs_pos.add(h.getheroPos());
			hs_cate.add(h.getheroCategory());
			hs_kind.add(h.getheroKind());
			hs_prop.add(h.getheroProp());
		}
		
		for (String unit : hs_nation) {
			nationDict.put(unit, unit);
		}
		for (String unit : hs_pos) {
			posDict.put(unit, unit);
		}
		for (String unit : hs_cate) {
			cateDict.put(unit, unit);
		}
		for (String unit : hs_kind) {
			kindDict.put(unit, unit);
		}
		for (String unit : hs_prop) {
			propDict.put(unit, unit);
		}
		long e = System.currentTimeMillis();
	}
	@RequestMapping("/getNationDict")
	@ResponseBody
	public JSONObject getNationDict() {
		return	nationDict;
	}
	@RequestMapping("/getPosDict")
	@ResponseBody
	public JSONObject getPosDict() {
		return	posDict;
	}
	@RequestMapping("/getCateDict")
	@ResponseBody
	public JSONObject getCateDict() {
		return	cateDict;
	}
	@RequestMapping("/getKindDict")
	@ResponseBody
	public JSONObject getKindDict() {
		return	kindDict;
	}
	@RequestMapping("/getPropDict")
	@ResponseBody
	public JSONObject getPropDict() {
		return	propDict;
	}
	
	@RequestMapping("/hero_s") //this is for server 分页
	@ResponseBody
	public JSONObject hero_s(HttpServletRequest req) {
		req.getParameterMap().forEach((k,v)->System.out.println("on fatherreq: " + k + " : " +v[0]));
		JSONArray res_arr = JSONUtil.parseArray(lhero,false);
		JSONObject  res_obj =  JSONUtil.createObj();
		res_obj.put("rows", res_arr);
		res_obj.put("total", res_arr.size());
		return	res_obj; //客户端分页必须返回这样的格式
	}
	
	@RequestMapping("/hero") //below if for client 分页
	@ResponseBody
	public JSONArray hero(HttpServletRequest req) {
		req.getParameterMap().forEach((k,v)->System.out.println("on fatherreq: " + k + " : " +v[0]));
		JSONArray res = JSONUtil.parseArray(lhero,false);
		return	res;
	}
	
	@RequestMapping("/hero_add")
	@ResponseBody
	public String hero_add(HttpServletRequest req) {
		String str = req.getParameter("para_newH");
		String jsonstr = JSONUtil.formatJsonStr(str );
		jsonstr =  jsonstr.replaceAll("_t", "");
		System.out.println(jsonstr);
		Hero ah  =  JSONUtil.parseObj(jsonstr).toBean(Hero.class);
		lhero.add(0, ah);
		return "okok";
	}
	@RequestMapping("/hero_del")
	@ResponseBody
	public String hero_del(HttpServletRequest req) {
		String ids = req.getParameter("para_delH");
		
		
		for(String i  : ids.split(",")) {
			int idss= Integer.parseInt(i);
			System.out.println(idss);
			for(Hero t : lhero) {
				if (t.getheroId() == idss) {
					lhero.remove(t);
					break;
				}
			}
		}
		return "okok";
	}
	@RequestMapping("/hero_update")
	@ResponseBody
	public String hero_update(HttpServletRequest req) {
		String str = req.getParameter("para_editH");
		String jsonstr = JSONUtil.formatJsonStr(str );
		Hero ah  =  JSONUtil.parseObj(jsonstr).toBean(Hero.class);
		int aim = ah.getheroId();
		
		for(Hero t : lhero) {
			if (t.getheroId() == aim) {
				int index = lhero.indexOf(t);
				lhero.set(index, ah);
				break;
			}
		}
		return "okok";
	}
	
	@RequestMapping("/hero_update_duo")
	@ResponseBody
	public String hero_update_duo(HttpServletRequest req) {
		String str = req.getParameter("para_editHduo");
		String jsonstr = JSONUtil.formatJsonStr(str );
		JSONArray jArray  =  JSONUtil.parseArray(jsonstr);
		List<Hero> lt2 =  JSONUtil.toList(jArray, Hero.class);
		for (Hero t2 : lt2) {
			System.out.println("rec: " + t2);
			for(Hero t : lhero) {
				if (t.getheroId() == t2.getheroId()) {
					int index = lhero.indexOf(t);
					lhero.set(index, t2);
					break;
				}
			}
		}
		return "okok";
	}
	
	//devil Mappings....
	@RequestMapping("/devil")
	@ResponseBody
	public JSONArray devil(HttpServletRequest req) {
		String key = req.getParameter("search_key");
		req.getParameterMap().forEach((k,v)->System.out.println("on sonReq: " + k + " : " +v[0]));
		List<Devil> res1 = new ArrayList<Devil>() ;
		for(Devil tmp : ldevil) {
			if (tmp.ref2hero == (Integer.parseInt(key))) {
				res1.add(tmp);
			}
		}
		JSONArray res = JSONUtil.parseArray(res1);
		return	res;
	}
	@RequestMapping("/devil_add")
	@ResponseBody
	public String devil_add(HttpServletRequest req) {
		String str = req.getParameter("para_newD");
		String jsonstr = JSONUtil.formatJsonStr(str );
		Devil ad  =  JSONUtil.parseObj(jsonstr).toBean(Devil.class);
		ldevil.add(0, ad);
		return "okok";
	}
	
	@RequestMapping("/devil_del")
	@ResponseBody
	public String devil_del(HttpServletRequest req) {
		String dId = req.getParameter("para_delD");
		for(Devil d : ldevil) {
			if (d.getDevilId() == Integer.parseInt(dId)) {
				ldevil.remove(d);
				break;
			}
		}
		return "okok";
	}
	
	@RequestMapping("devil_update")
	@ResponseBody
	public String devil_update(HttpServletRequest req) {
		String str = req.getParameter("para_editD");
		String jsonstr = JSONUtil.formatJsonStr(str );
		Devil ad  =  JSONUtil.parseObj(jsonstr).toBean(Devil.class);
		int aim = ad.getDevilId();
		
		for(Devil d : ldevil) {
			if (d.getDevilId() == aim) {
				int index = ldevil.indexOf(d);
				ldevil.set(index, ad);
				System.out.println("edit done with: " + ad);
				break;
			}
		}
		return "okok";
	}
}
