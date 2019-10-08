package testJSON;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import testModel.fir;
import testModel.sec;

class ttt {
	public int id = 10;
}
public class testJSONParse {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		fir f = new fir();
		sec s = new sec();
		
		JSONObject jf = JSONUtil.parseObj(f);
		JSONObject js = JSONUtil.parseObj(s);
		
		System.out.println(jf.toString());
		System.out.println(js.toString());
		jf.put("alias", js.getStr("alias"));
		System.out.println(jf.toString());
		
		fir f1 = JSONUtil.parseObj(jf.toString()).toBean(fir.class);
		System.out.println(JSONUtil.parseObj(f1).toString());
	}

}