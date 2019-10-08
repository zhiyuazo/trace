package testPinyin;

import org.junit.Test;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import pinyin.PinyinUtil;

public class testPinyin {
	public void t2() throws BadHanyuPinyinOutputFormatCombination {
		PinyinUtil.setFormatPinyin("small","no","unicode");
		String s=PinyinUtil.parseFullHeadPinyin("王军游泳");
		System.out.println(s);
	}
	
	@Test
	public void t3() {
		JSONObject tmp = JSONUtil.createObj();
		tmp.put("id", "中国");
		tmp.put("text", "中国");
		System.out.println(tmp.toString());
		System.out.println(tmp.get("id"));
		
	}
	
	
}
