package	 com.zzy.trace.bean; 

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;

public class Devil {
	String[] devilName_s = new String[] {"守护者艾格文","安度因洛萨","安东尼达斯","吉安娜",
			"克尔苏加德","卡德加","麦迪文","埃兰","罗宁",
			"乌瑟尔","铜须","地狱咆哮","萨尔","杜隆坦",
			"古尔丹","耐奥祖","基尔罗格","凯恩血蹄","艾萨拉",
			"玛法里奥","泰兰德","萨格拉斯","阿克蒙德","基尔加丹",
			"玛诺洛斯","玛里苟斯","伊瑟拉","阿尔萨斯","诺兹多姆",
			"图拉扬","伊瑟拉","伊利丹","希尔瓦娜斯"};
String[] devilNation_s = new String[] {"艾泽拉斯","暮色森林","逆风小径","艾尔文森林",
			"死亡矿井","纳格兰","闪金镇","暴风要塞","赤脊山脉",
			"荆棘谷","悲伤沼泽","卡利姆多","灰谷","达纳苏斯",
			"塞纳留斯","海加尔山","莫高雷","雷霆崖","铁炉堡",
			"洛丹伦","奥特兰克山脉","斯坦索姆","诺森德","太阳之井",
			"银月城","影牙城堡","血色十字军","地狱火半岛","贫瘠之地",
			"费伍德森林","冬泉谷","加基森"};
String[] devilDesc_s = new String[] {"最近缺蓝..","缺少精神..","已被标记...","已消灭...",
		  "正在攻打...","比较厉害","一般般，不足为患","暴怒状态...",
		  "威胁值高...","已死亡","准备逃跑","武力值碾压"};
String[] devilPos_s = new String[] {"米奈希尔港","奥伯丁码头","卢瑟兰港口","藏宝海湾码头","塞拉摩港口","暴风港口","荆齿成码头","海象人码头","瓦加德港口"};
String[] devilEquipLevel_s = new String[] {"灰色粗糙","白色普通","绿色优秀","蓝色精良","紫色史诗","橙色传说"};
String[] devilKind_s = new String[] {"人类","亡灵","巨魔","牛头人","暗夜精灵","兽人","矮人","侏儒","德莱尼"};
String[] devilProp_s = new String[] {"敏捷","耐力","暴击","火","冰","精神","智力","急速","力量","精通"};
	
	public int devilId ;
	public String devilName ;
	public String devilDesc ;
	public String devilNation ;
	public String devilEquipLevel ;
	public String devilKind ;
	public String devilCate ;
	public String getDevilCate() {
		return devilCate;
	}

	public void setDevilCate(String devilCate) {
		this.devilCate = devilCate;
	}

	public int ref2hero;
	
	public int getRef2hero() {
		return ref2hero;
	}

	public void setRef2hero(int ref2hero) {
		this.ref2hero = ref2hero;
	}

	public Devil(int id) {
		devilId = id;
		devilName	=devilName_s[ RandomUtil.randomInt(devilName_s.length) ];
		devilDesc	=Convert.toBool(RandomUtil.randomInt(2))?"":devilDesc_s[ RandomUtil.randomInt(devilDesc_s.length) ];
		devilNation =	Convert.toBool(RandomUtil.randomInt(2))?"": devilNation_s[ RandomUtil.randomInt(devilNation_s.length) ];
		devilEquipLevel =Convert.toBool(RandomUtil.randomInt(2))?"":devilEquipLevel_s[ RandomUtil.randomInt(devilEquipLevel_s.length) ];
		devilKind=Convert.toBool(RandomUtil.randomInt(2))?"":devilKind_s[ RandomUtil.randomInt(devilKind_s.length) ];
	}
	
	public String toString() {
		String[] tmp = new String[] {
									String.valueOf(devilId),
									devilName,
									devilDesc,
									devilNation,
									devilEquipLevel,
									devilKind,
								};
		return String.join("_", tmp);
	}

	public int getDevilId() {
		return devilId;
	}

	public void setDevilId(int devilId) {
		this.devilId = devilId;
	}

	public String getDevilName() {
		return devilName;
	}

	public void setDevilName(String devilName) {
		this.devilName = devilName;
	}

	public String getDevilDesc() {
		return devilDesc;
	}

	public void setDevilDesc(String devilDesc) {
		this.devilDesc = devilDesc;
	}

	public String getDevilNation() {
		return devilNation;
	}

	public void setDevilNation(String devilNation) {
		this.devilNation = devilNation;
	}

	public String getDevilEquipLevel() {
		return devilEquipLevel;
	}

	public void setDevilEquipLevel(String devilEquipLevel) {
		this.devilEquipLevel = devilEquipLevel;
	}

	public String getDevilKind() {
		return devilKind;
	}

	public void setDevilKind(String devilKind) {
		this.devilKind = devilKind;
	}

	
}