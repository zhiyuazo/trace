package testJSON;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import testModel.fatherInner;

public class testJSONInnerClass {
	public static void main(String[] args) {
		fatherInner ff = new fatherInner();
		JSONObject  o = JSONUtil.parseObj(new fatherInner(),true,true);
		System.out.println(o.toStringPretty());
	}
}

