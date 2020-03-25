package date;

import java.io.IOException;
import java.text.ParseException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class testClock {
	public static void main(String[] args) throws InterruptedException, ParseException, IOException {
//		//不同时区获取的获取的时间戳都一样.....
		Clock c = Clock.system(ZoneId.of("GMT"));
		Clock c1 = Clock.systemDefaultZone();
		Clock c2  = Clock.systemUTC();
		Clock c3 = Clock.system(ZoneId.of("GMT+08:00"));
		System.out.printf("%s\t%s\t%s\t\n",c.toString() , c.millis() ,c.getZone());
		System.out.printf("%s\t%s\t%s\t\n",c1.toString() , c1.millis() ,c1.getZone());
		System.out.printf("%s\t%s\t%s\t\n",c2.toString() , c2.millis() ,c2.getZone());
		System.out.printf("%s\t%s\t%s\t\n",c3.toString() , c3.millis() ,c3.getZone());
//		//用时间戳表示时间日期，不带时区or偏移
		System.out.println(LocalDateTime.ofEpochSecond(c.millis()/1000, 0, ZoneOffset.UTC)); //通过时间戳获取本地时间...符合认知
		System.out.println(LocalDateTime.ofEpochSecond(c.millis()/1000, 0, ZoneOffset.ofHours(8))); //通过时间戳获取本地时间...符合认知
		
		System.out.println( c.tickSeconds(ZoneId.of("GMT")).millis() );
		System.out.println( c.tickSeconds(ZoneId.of("Asia/Shanghai")).millis());
		System.out.println( c.tickSeconds(ZoneId.of("Z")).millis());
		System.out.println( c.tickSeconds(ZoneId.of("GMT+08:00")).millis());
		
		
	}
}