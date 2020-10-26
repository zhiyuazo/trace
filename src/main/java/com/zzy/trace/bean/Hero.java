package com.zzy.trace.bean; 

import java.util.Arrays;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;

public class Hero {
	String[] heroName_s = new String[] {"守护者艾格文","安度因洛萨","安东尼达斯","吉安娜",
											"克尔苏加德","卡德加","麦迪文","埃兰","罗宁",
											"乌瑟尔","铜须","地狱咆哮","萨尔","杜隆坦",
											"古尔丹","耐奥祖","基尔罗格","凯恩血蹄","艾萨拉",
											"玛法里奥","泰兰德","萨格拉斯","阿克蒙德","基尔加丹",
											"玛诺洛斯","玛里苟斯","伊瑟拉","阿尔萨斯","诺兹多姆",
											"图拉扬","伊瑟拉","伊利丹","希尔瓦娜斯"};
	String[] heroNation_s = new String[] {"艾泽拉斯","暮色森林","逆风小径","艾尔文森林",
											"死亡矿井","纳格兰","闪金镇","暴风要塞","赤脊山脉",
											"荆棘谷","悲伤沼泽","卡利姆多","灰谷","达纳苏斯",
											"塞纳留斯","海加尔山","莫高雷","雷霆崖","铁炉堡",
											"洛丹伦","奥特兰克山脉","斯坦索姆","诺森德","太阳之井",
											"银月城","影牙城堡","血色十字军","地狱火半岛","贫瘠之地",
											"费伍德森林","冬泉谷","加基森"};
	String[] heroDesc_s = new String[] {"最近缺蓝..","缺少精神..","已被标记...","已消灭...",
										  "正在攻打...","比较厉害","一般般，不足为患","暴怒状态...",
										  "威胁值高...","已死亡","准备逃跑","武力值碾压"};
	String[] heroPos_s = new String[] {"米奈希尔港","奥伯丁码头","卢瑟兰港口","藏宝海湾码头","塞拉摩港口","暴风港口","荆齿成码头","海象人码头","瓦加德港口"};
	String[] heroEquipLevel_s = new String[] {"灰色粗糙","白色普通","绿色优秀","蓝色精良","紫色史诗","橙色传说"};
	String[] heroCategory_s = new String[] {"法师","牧师","盗贼","术士","战士","圣骑士","猎人","德鲁伊","死亡骑士","萨满"};
	String[] heroKind_s = new String[] {"人类","亡灵","巨魔","牛头人","暗夜精灵","兽人","矮人","侏儒","德莱尼"};
	String[] heroProp_s = new String[] {"敏捷","耐力","暴击","火","冰","精神","智力","急速","力量","精通"};
	
	public int      heroId;
	public String   heroName;
	public String   heroNation; 
	public String   heroDesc;
	public String   heroPos ;
	public String   heroEquipLevel;
	public String   heroCategory;
	public String   heroKind;
	public String   heroProp ;
	
	
	public Hero(int id) {
		heroId  = id ; 
		heroName= heroName_s[ RandomUtil.randomInt(heroName_s.length) ];
		heroNation	=		  heroNation_s[ RandomUtil.randomInt(heroNation_s.length) ];
		heroDesc		=	  heroDesc_s[ RandomUtil.randomInt(heroDesc_s.length) ];
		heroPos			=  heroPos_s[ RandomUtil.randomInt(heroPos_s.length) ];
		heroEquipLevel	=		  heroEquipLevel_s[ RandomUtil.randomInt(heroEquipLevel_s.length) ];
		heroCategory		=	  heroCategory_s[ RandomUtil.randomInt(heroCategory_s.length) ];
		heroKind		=	  heroKind_s[ RandomUtil.randomInt(heroKind_s.length) ];
		heroProp		=	  heroProp_s[ RandomUtil.randomInt(heroProp_s.length) ];
	}
	
	public int getheroId() {
		return heroId;
	}
	public void setheroId(int heroId) {
		this.heroId = heroId;
	}
	public String getheroName() {
		return heroName;
	}
	public void setheroName(String heroName) {
		this.heroName = heroName;
	}
	public String getheroNation() {
		return heroNation;
	}
	public void setheroNation(String heroNation) {
		this.heroNation = heroNation;
	}
	public String getheroDesc() {
		return heroDesc;
	}
	public void setheroDesc(String heroDesc) {
		this.heroDesc = heroDesc;
	}
	public String getheroPos() {
		return heroPos;
	}
	public void setheroPos(String heroPos) {
		this.heroPos = heroPos;
	}
	public String getheroEquipLevel() {
		return heroEquipLevel;
	}
	public void setheroEquipLevel(String heroEquipLevel) {
		this.heroEquipLevel = heroEquipLevel;
	}
	public String getheroCategory() {
		return heroCategory;
	}
	public void setheroCategory(String heroCategory) {
		this.heroCategory = heroCategory;
	}
	public String getheroKind() {
		return heroKind;
	}
	public void setheroKind(String heroKind) {
		this.heroKind = heroKind;
	}
	public String getheroProp() {
		return heroProp;
	}
	public void setheroProp(String heroProp) {
		this.heroProp = heroProp;
	}
	public String toString() {
		  String[] res  = new String[] { String.valueOf(heroId), heroName, heroNation,
		                   heroDesc,heroPos ,
		                   heroEquipLevel, heroCategory, heroKind,
		                   heroProp}; 
		  return Arrays.toString(res);
	}
}
