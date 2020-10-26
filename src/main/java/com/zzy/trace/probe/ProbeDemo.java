package com.zzy.trace.probe;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.zzy.trace.bean.Hero;

import cn.hutool.json.JSONUtil;

public class ProbeDemo {
	public static void main(String[] args) throws InterruptedException {
		
		int a = 111451; //不可转化为JsonStr
		Integer b = 24568;//不可转化为JsonStr
		String c = "3123142141413213131";//OK转化为JsonStr
		boolean flag = true;  //不可转化为JSon
		Date  dd = new Date();
		
		Hero ll = new Hero(12);
		ll.heroId = 100;
		ll.heroDesc = "zhiyuan";
		Object  tmsg = ll;
		String test = JSONUtil.toJsonStr(dd.toString());
		System.out.println(test);
		
		String msgstr = "hello";
		
		for(int i = 0 ; i< 3 ; i++){
			//msg-1
			ProbeUtil.getInstance().addProbe(new Probe("M221","接收","String",ll));
			TimeUnit.MILLISECONDS.sleep(150);
			ProbeUtil.getInstance().addProbe(new Probe("M221","转换","String",ll));
			TimeUnit.MILLISECONDS.sleep(150);
//			ProbeUtil.getInstance().addProbe(new Probe("M221","矫正","String",ll));
//			TimeUnit.MILLISECONDS.sleep(150);
//			ProbeUtil.getInstance().addProbe(new Probe("M221","赋值","String",ll));
//			TimeUnit.MILLISECONDS.sleep(150);
//			ProbeUtil.getInstance().addProbe(new Probe("M221","发送","String",ll));
//			TimeUnit.MILLISECONDS.sleep(150);
			
			//msg-2
//			ProbleUtil.getInstance().addProbe(new Probe("M22","接收","String",ll));
//			TimeUnit.MILLISECONDS.sleep(150);
//			ProbleUtil.getInstance().addProbe(new Probe("M22","转换","String",ll));
//			TimeUnit.MILLISECONDS.sleep(150);
//			ProbleUtil.getInstance().addProbe(new Probe("M22","矫正","String",ll));
//			TimeUnit.MILLISECONDS.sleep(150);
//			ProbleUtil.getInstance().addProbe(new Probe("M22","赋值","String",ll));
//			TimeUnit.MILLISECONDS.sleep(150);
//			ProbleUtil.getInstance().addProbe(new Probe("M22","发送","String",ll));
//			TimeUnit.MILLISECONDS.sleep(150);
		}
		ArrayList<MsgProbeDesc> res = ProbeUtil.getInstance().getProbeResult();
		System.out.println(res.get(0).getTimeDead());
		
		System.out.println(JSONUtil.toJsonPrettyStr(res));
		
		
		
		
		
	}

}
