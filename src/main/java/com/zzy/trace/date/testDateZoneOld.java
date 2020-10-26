package com.zzy.trace.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class testDateZoneOld {

	//moment.js  不指定utc的情况下，默认得到的时间为系统所在时区的时间。
	//picker.endDate.format("YYYY-MM-DD HH:mm:ss"); 得到的是一个时间字符串， 如果moment.js 用utc，则为UTC字符串，如果不是，则为系统时间字符串
	//java 收到字符串
	//DateFormat.getDateTimeInstance().parse(字符串[不带时区])，得到UTC Date 对象  
	//DateFormat.getDateTimeInstance().parse(字符串[不带时区])，得到UTC Date 对象 
//					[DateTimeInstance] 可以解析时间
//					[DateInstances] 不能解析时间
	
//		for(String tmp : TimeZone.getAvailableIDs()) { java 得到可用的时区
//			System.out.println(tmp);
//		}
	
	public static void main(String[] args) {
		String v =  "2019-09-20 10:00:00";
		String v1 =  "2019-09-20 10:00:00.654 08:00";
		System.out.println("获得默认时区 " + TimeZone.getDefault());
		System.out.println("Zone-Offset= " + Calendar.getInstance().get(Calendar.ZONE_OFFSET));
		try {
			Date d = DateFormat.getDateTimeInstance().parse(v);
			System.out.println(d.getTime());
			
			Date d1 = DateFormat.getDateTimeInstance().parse(v1);
			System.out.println(d1.getTime());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("修改时区为日本....");
		TimeZone.setDefault(TimeZone.getTimeZone("Japan"));
		System.out.println("获得时区 " + TimeZone.getDefault());
		System.out.println("Zone-Offset= " + Calendar.getInstance().get(Calendar.ZONE_OFFSET));
		
		try {
			Date d = DateFormat.getDateTimeInstance().parse(v);
			System.out.println(d.getTime());
			
			Date d1 = DateFormat.getDateTimeInstance().parse(v1);
			System.out.println(d1.getTime());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
