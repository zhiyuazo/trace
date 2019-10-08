package testJSON;

import java.sql.SQLException;

public class testJSONnoGetterSetter {
	
	//#######################################################################################################
	//####疑问.....TsTrackPoint 没有getter and setter 方法 是怎么在对象和 JSON(String) 之间转化的？ ValueFilter 测试不行啊？
	//#######################################################################################################
	public static void main(String[] args) throws SQLException {
		//##################对象转JSONString,Ali 提供一种方法不需要提供Get Set??? Hutool不提供....
		//alibaba
		com.alibaba.fastjson.serializer.ValueFilter  filter = new com.alibaba.fastjson.serializer.ValueFilter() {
			@Override
			public Object process(Object object, String name, Object value) {
				if (value == null)
					return "";
				return value;
			}
		};
		String ali_special = com.alibaba.fastjson.JSON.toJSONString(new yclass(), filter);
		System.out.println(ali_special);
		//hutool
		//......................................
		//...............not Support....................
		//......................................
		
		//##################对象转JSONString正常情况下  都需要Get Set....方法
		System.out.println("---------------------------------------------------------------------------------");
		//alibaba
		String ali_xres = com.alibaba.fastjson.JSON.toJSONString(new xclass());
		System.out.println(ali_xres);
		
		String ali_yres = com.alibaba.fastjson.JSON.toJSONString(new yclass());
		System.out.println(ali_yres);
		
		//hutool
		String hut_xres = cn.hutool.json.JSONUtil.toJsonStr(new xclass());
		System.out.println(hut_xres);
		
		String hut_yres = cn.hutool.json.JSONUtil.toJsonStr(new yclass());
		System.out.println(hut_yres);
		
		//##################对象转JSON对象  都需要Get Set....方法
		System.out.println("---------------------------------------------------------------------------------");
		//alibaba
		com.alibaba.fastjson.JSON ali_json_xobj = (com.alibaba.fastjson.JSON) com.alibaba.fastjson.JSON.toJSON(new xclass());
		System.out.println(ali_json_xobj.toJSONString());
		
		com.alibaba.fastjson.JSON ali_json_yobj = (com.alibaba.fastjson.JSON) com.alibaba.fastjson.JSON.toJSON(new yclass());
		System.out.println(ali_json_yobj.toJSONString());
		
		//hutool
		cn.hutool.json.JSONObject hut_json_xobj = cn.hutool.json.JSONUtil.parseObj(new xclass());
		System.out.println(hut_json_xobj.toString());
		
		cn.hutool.json.JSONObject hut_json_yobj = cn.hutool.json.JSONUtil.parseObj(new yclass());
		System.out.println(hut_json_yobj.toString());
		
		//##################json字符串 转 对象  都需要Get Set....方法
		System.out.println("---------------------------------------------------------------------------------");
		//alibaba
		xclass  ali_xobj = com.alibaba.fastjson.JSON.parseObject("{\"age\":99,\"big\":true,\"name\":\"xx\"}", xclass.class);
		System.out.println(ali_xobj.age);
		
		yclass  ali_yobj = com.alibaba.fastjson.JSON.parseObject("{\"age\":99,\"big\":true,\"name\":\"yy\"}", yclass.class);
		System.out.println(ali_yobj.age);
		
		//hutool
		try {
			xclass hut_xobj = cn.hutool.json.JSONUtil.toBean("{\"age\":99,\"big\":true,\"name\":\"xx\"}", xclass.class);
			System.out.println(hut_xobj.toString());
		}catch(Exception e) {
			System.out.println("error");
		}
		yclass hut_yobj = cn.hutool.json.JSONUtil.toBean("{\"age\":99,\"big\":true,\"name\":\"yy\"}", yclass.class);
		System.out.println(hut_yobj.age);
	}
}

class xclass {
	String name = "x" ; 
	int age = 29;
	boolean big = false; 
}
class yclass {
	String name = "y" ; 
	int age = 19;
	boolean big = true;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isBig() {
		return big;
	}
	public void setBig(boolean big) {
		this.big = big;
	} 
	
}
