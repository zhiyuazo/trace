package testJSON;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import testModel.son;

public class testJSONwithParentandSon {
	
	public static void main(String[] args) {
		JSONObject j = JSONUtil.parseObj(new son());
		System.out.println(j.toString());
		
	}
}