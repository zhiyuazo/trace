package pinyin;
import java.util.Arrays;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
	public static HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
	
	static {
			//大写/小写,2个选项,LOWERCASE,UPPERCASE
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			//声调标志是否显示,3个选项,WITH_TONE_NUMBER(wo3),WITHOUT_TONE(wo),WITH_TONE_MARK(..)
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			//碰到 u,v u的显示方式,3个选项, WITH_U_AND_COLON(u:->表示u)  WITH_U_UNICODE(u->表示u)  WITH_V(v->表示u)
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
	}
	
	//set format of parse pinyin 
	public static void setFormatPinyin(String bigsmall , String tone, String ushow) {
		switch (bigsmall) {
		case "big":
			format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
			break;
		case "small":
			format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			break;
		default:
			format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			break;
		}
		
		switch (tone) {
		case "number":
			format.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
			break;
		case "mark":
			format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
			break;
		case "no":
			format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			break;
		default:
			format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			break;
		}
		
		switch (ushow) {
		case "colon":
			format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);
			break;
		case "unicode":
			format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
			break;
		case "v":
			format.setVCharType(HanyuPinyinVCharType.WITH_V);
			break;
		default:
			format.setVCharType(HanyuPinyinVCharType.WITH_V);
			break;
		}
		
	}
	
	//get format of parse pinyin 
	public static String[] getFormatPinyin() {
		String[] res = new String[3];
		res[0] = format.getToneType().getName();
		res[1] = format.getVCharType().getName();
		res[2] = format.getCaseType().getName();
		return res;
	}
	
	//Convert to pinyin with full&Head spell, with | separator...  
	public static String  parseFullHeadPinyin(String s) {
		char[]  cArr = s.toCharArray();
		String resFull = "";
		String resHead = "";
		try {
			for (int i = 0; i < cArr.length; i++) {
				if(Character.toString(cArr[i]).matches("[\\u4E00-\\u9FA5]+")) {
					resFull += PinyinHelper.toHanyuPinyinStringArray(cArr[i], format)[0];
					resHead+= PinyinHelper.toHanyuPinyinStringArray(cArr[i], format)[0].charAt(0);
				}else {
					resFull += Character.toString(cArr[i]);
					resHead += Character.toString(cArr[i]);
				}
			}
		}catch(BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return resFull + "|" + resHead;
	}
	
	//Convert to pinyin with full spell  
	public static String  parseFullPinyin(String s) {
		char[]  cArr = s.toCharArray();
		String res = "";
		try {
			for (int i = 0; i < cArr.length; i++) {
				if(Character.toString(cArr[i]).matches("[\\u4E00-\\u9FA5]+")) {
					res += PinyinHelper.toHanyuPinyinStringArray(cArr[i], format)[0];
				}else {
					res += Character.toString(cArr[i]);
				}
			}
		}catch(BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return res;
	}
	
	//Convert to pinyin with head spell  
	public static String  parseHeadPinyin(String s) throws BadHanyuPinyinOutputFormatCombination {
		String res = ""; 
		for(int i=0;i<s.length();i++) {
			char word = s.charAt(i);
			String[] pyArr = PinyinHelper.toHanyuPinyinStringArray(word,format);
			if(pyArr != null) {
				res += pyArr[0].charAt(0);
			}else {
				res += word;
			}
		}
		return res;
	}
	
	//Convert to ASCII code  
	public static String  parseAsciiPinyin(String s) throws BadHanyuPinyinOutputFormatCombination {
		StringBuffer  sb =  new StringBuffer();
		byte[] bgbk = s.getBytes();
		
		for (int i = 0; i < bgbk.length; i++) {
			sb.append(Integer.toHexString(bgbk[i] & 0xff));
		}
		return sb.toString();
	}
	
	//test function
	public static  void testPinyin() throws BadHanyuPinyinOutputFormatCombination {
		String s = "爱";
		System.out.println(s.charAt(0));
		System.out.println(s.codePointAt(0));
		
//		toHanyuPinyinString[Array|""] 3种方法
//				(char) |
//				(char,format) | 
//				(char,format,string)[deprecate]
		String[] res = null;
		res = PinyinHelper.toHanyuPinyinStringArray('折');
		System.out.println(Arrays.toString(res));
		
		res = PinyinHelper.toGwoyeuRomatzyhStringArray('折');
		System.out.println(Arrays.toString(res));
		
		res = PinyinHelper.toMPS2PinyinStringArray('折');
		System.out.println(Arrays.toString(res));
				
		res = PinyinHelper.toTongyongPinyinStringArray('折');
		System.out.println(Arrays.toString(res));
		
		res = PinyinHelper.toWadeGilesPinyinStringArray('折');
		System.out.println(Arrays.toString(res));
		
		res = PinyinHelper.toYalePinyinStringArray('折');
		System.out.println(Arrays.toString(res));
	}
	
	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
		PinyinUtil.testPinyin();
	}
	
}
