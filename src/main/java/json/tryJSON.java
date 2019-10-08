package json;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class tryJSON {
	public static void main(String[] args) {
		String structure = "{\"sec_tree_target\":[{\"label\":\"侦察目标\",\"children\":[]}]}";
		JSONObject  top  = JSONUtil.parseObj(structure,false,true);
		System.out.println(top.toString());
	}
}
