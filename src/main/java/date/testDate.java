package date;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class testDate {
	private static final Logger logger = LoggerFactory.getLogger(testDate.class);
	//ISO8601标准规定:2010-06-06T10:00:00.999Z [T]表示时期时间连接符 [Z]表示该时间为UTC时间
	//DST  夏令时(Daylight saving time)地方时间制度 ,只是拨快了时间，更好的利用白天
	//UTC  现.协调世界时间(原子计时) [coordinated universal time] UTC = GMT +(-) 0.9s;
	//GMT  前.世界标准时间 ()  [Greenwich mean time 格林尼治] 也成为[UT : universal time]
	//CST  美国|澳大利也|古巴|北京标准时间 (Central Standard Time)  
//		CST(USA) 美国标准时间 UT-6:00
//		CST(Australia) 澳大利亚标准时间 UT+9:30
//		CST(China Standar Time) 中国标准时间 UT+8:00
//		CST(Cuba Stander Time) 古巴标准时间  UT-4:00 
//		1.北京 = GMT+8  or UTC+8
//		2.美国 = GMT-5  or UTC-5
//		3.日本 = GMT+9  or UTC+9
	//IST  印度|以色列标准时间 (India|Israel Standard Time)  
	public static void main(String[] args) throws ParseException {
		//Before JAVA1.8
		//Date()       包含日期+时间   {PS:可变类型}
		//Calendar --> 用于-日期和时间字段的转换... 
//				1.Calendar.getInstance 
//				2.GregorianCalendar  提供世界大多数国家使用的标准日历系统 
		//DateFormat(SimpleDateFormat) --> 用于 格式化和分析日期字符串... (即日期<-->文本 相互转化 ) {PS:非线程安全...}
		
		logger.info("new Date().. 代表");
		Date  d = new Date();
		System.out.printf("\td.toGMTString()=\t%s\n",d.toGMTString()); //GMT 时间
		System.out.printf("\td.toInstant()=\t%s\n",d.toInstant()); //UTC时间
		System.out.printf("\td.toString()=\t%s\n",d.toString()); //本地标准时间
		System.out.printf("\td.toLocaleString()=\t%s\n",d.toLocaleString()); //本地时间格式
		
		logger.info("new Date(long mills).. 代表");
		Date d1 = new Date(86400000);
		System.out.printf("\td1.toGMTString()=\t%s\n",d1.toGMTString()); //GMT 时间
		System.out.printf("\td1.toInstant()=\t%s\n",d1.toInstant()); //UTC时间
		System.out.printf("\td1.toString()=\t%s\n",d1.toString()); //本地标准时间
		System.out.printf("\td1.toLocaleString()=\t%s\n",d1.toLocaleString()); //本地时间格式
		logger.info("Date.. 可用方法");
		d.toString();
		d.after(d1);
		d.before(d1);
		d.compareTo(d1);
		d.equals(d1);
		d.getTime();
		d.setTime(123456789);
		d.toInstant();
		d.from(Instant.now());
		d.clone() ;
		
		logger.info("SimpleDateFormat.. 字符串<-->Date()转换");
//		G:年代缩写如-公元xx  , y:年份  , Y:周年 (几个y|Y 无所谓)
//		M:月份 , L:月份
//		w:周in年  ,  W:周in月
//		D:天in年 ,  d:天in月
//		F:?  , E:星期几  ,u:一周中的第几天   a:上午/下午
//		H:小时24(0-23), k:小时24(1-24)  
//		K:小时12(1-12), h:小时12(1-12)
//		m:分钟 , s秒  ,  S:毫秒
//		z:时区名称CST,  Z:时区+0800, X:时区缩写 +08
		String dformat = "z | Z | X yyyy-MM-dd HH:mm:ss";
		Date d2  = new Date();
		SimpleDateFormat format = new SimpleDateFormat(dformat);
		String formatRes = format.format(d2);
		System.out.printf("\tSimpleDateFormat(%s).format(Date)=\t%s\n",dformat,formatRes); 
		System.out.printf("\tDate()=SimpleDateFormat(%s).parse(String)=\t%s\n",dformat,format.parse(formatRes).toLocaleString()); 
		
		logger.info("Calendar|GregorianCalendar.. 日期时间转换");
		Calendar now1 = Calendar.getInstance(Locale.CHINESE);
		Calendar now2 = Calendar.getInstance(Locale.US);
		Calendar now3 = Calendar.getInstance(TimeZone.getTimeZone("GMT-:8:00"));
		Calendar now4 = Calendar.getInstance(TimeZone.getTimeZone("GMT-:8:00"));
		Calendar now5 = new GregorianCalendar();
		Calendar now6 = new GregorianCalendar(Locale.US);
		Calendar now7 = new GregorianCalendar(TimeZone.getTimeZone("GMT-:8:00"));
		Calendar now8 = new GregorianCalendar(TimeZone.getTimeZone("GMT-:8:00"),Locale.US);
		Calendar now9 = new GregorianCalendar(2016,01,01);
		Calendar now10 = new GregorianCalendar(2016,01,01,15,50);
		Calendar now11 = new GregorianCalendar(2016,01,01,15,50,59);
		
		System.out.printf("\tnow2.get(Calendar.HOUR)=\t%s\n",now2.get(Calendar.HOUR_OF_DAY)); 
		System.out.printf("\tnow2.get(Calendar.MINUTE)=\t%s\n",now2.get(Calendar.MINUTE)); 
		now5.setTime(new Date());
		
		//JAVA1.8 基于ISOb标准,java.time 包下的所有类都是不可变，且线程安全
		//DateTimeFormatter 【解析格式化日期时间】
		//Instant       【瞬时(时间戳类)】约等于util.Date类，可以相互转换 
		//YearMonth     【每年固定月份,应用于信用开到期等】
		//MonthDay      【每月固定天数，应用于重复时间，如生日，万圣节等】
		//LocalDate     【日期】 2020-03-01  	【不包含时间】
		//LocalTime     【时间】  15:15:55  	【不包含日期】
		//LocalDateTime 【日期时间】  2020-03-01 15:15:55 【但没有时差和时区信息】
		//ZonedDateTime 【日期时间】  2020-03-01 15:15:55 【含有完整的时区信息和相对UTC/GMT的时差信息】某时区下的时间
//						 【↑涵盖夏令时调整的规则】
		//OffsetDateTime 【日期时间】带有时差，不含时区
		//ZoneOffSet    【表示时区】
		//ZoneId		 【用来获取时区】
		//Clock          【用于获取时间戳】 替代  System.CurrentTimeMillis() 和 TimeZone.getDefault();
		//Duration 		【长短】带秒？
//			1.Duration.between(LocalDate-1,LocalDate-2) 
//			2.Duration.between(LocalTime-1,LocalTime-2) 
//			3.Duration.between(LocalDateTime-1,LocalDateTime-2)  
		//Period        【周期】带天？
//			1.Duration.between(LocalDate-1,LocalDate-2) 
		
		logger.info("JAVA8.. 日期");
		LocalDate  lodate  =  LocalDate.now();
		System.out.printf("\tLocalDate.now()=\t%s,年=%s|月=%s|日=%s\n",lodate,lodate.getYear(),lodate.getMonthValue(),lodate.getDayOfMonth()); 
		
		LocalDate  lodate2  =  LocalDate.of(2020, 4, 1);
		System.out.printf("\tLocalDate.now()=\t%s,年=%s|月=%s|日=%s\n",lodate2,lodate2.getYear(),lodate2.getMonthValue(),lodate2.getDayOfMonth()); 
		System.out.printf("\tlodate==lodate2? is:%s\n",lodate.equals(lodate2)); 
		
		LocalDate  lodate3  = lodate.plusDays(68); 
		System.out.printf("\tLocalDate.plusDays(68)=\t%s,年=%s|月=%s|日=%s\n",lodate3,lodate3.getYear(),lodate3.getMonthValue(),lodate3.getDayOfMonth()); 
		LocalDate  lodate4  = lodate.plus(1,ChronoUnit.WEEKS); 
		System.out.printf("\tLocalDate.plus(1,ChronoUnit.WEEKS)=\t%s,年=%s|月=%s|日=%s\n",lodate4,lodate4.getYear(),lodate4.getMonthValue(),lodate4.getDayOfMonth()); 
		LocalDate  lodate5  = lodate.minus(1,ChronoUnit.YEARS); 
		System.out.printf("\tLocalDate.minus(1,ChronoUnit.YEARS)=\t%s,年=%s|月=%s|日=%s\n",lodate5,lodate5.getYear(),lodate5.getMonthValue(),lodate4.getDayOfMonth()); 
		
		
		logger.info("JAVA8.. 时间");
		LocalTime lotime = LocalTime.now();
		System.out.printf("\tLocalTime.now()=\t%s,时=%s|分=%s|秒=%s\n",lotime,lotime.getHour(),lotime.getMinute(),lotime.getSecond()); 
		LocalTime newtime = lotime.plusHours(3);
		System.out.printf("\tLocalTime.now()=\t%s,时=%s|分=%s|秒=%s\n",newtime,newtime.getHour(),newtime.getMinute(),newtime.getSecond()); 
		
		logger.info("JAVA8.. 时间戳Clock");
		Clock clock = Clock.systemUTC();
		Clock defaultclock = Clock.systemDefaultZone();
		System.out.printf("\tClock.systemUTC()=%s,Clock.systemDefaultZone()=%s\n",clock.millis(),defaultclock.millis());
		
		Instant  timestamp = Instant.now();
		System.out.printf("\tInstant.now()时间戳=%s\n",timestamp);
		
		logger.info("JAVA8.. [月日]，[年日]");
		MonthDay  monday  = MonthDay.of(5, 1);
		MonthDay  monday2 = MonthDay.from(LocalDate.now());
		System.out.printf("\tmonday2==monday? is:%s\n",monday2.equals(monday)); 
	
		YearMonth currentYearMonth = YearMonth.now();
		System.out.printf("\tYearMonth=%s,当月共有天数=%s\n",currentYearMonth,currentYearMonth.lengthOfMonth()); 
		YearMonth expireYearMonth  = YearMonth.of(2020, 4);
		System.out.printf("\tYearMonth=%s,当月最后一天=%s\n",expireYearMonth,expireYearMonth.atEndOfMonth()); 
		
		
		logger.info("JAVA8.. 计算时间差");
		LocalDate former_lodate  = LocalDate.of(2018, 12, 10);
		LocalDate now_lodate  = LocalDate.now();
		Period  diff_by_period  =  Period.between(former_lodate, now_lodate);
		System.out.printf("\t现在时间=%s,过去时间=%s,差值=%s\n",now_lodate,former_lodate,diff_by_period);
		
		LocalTime former_lotime  = LocalTime.of(12, 10,21);
		LocalTime now_lotime  = LocalTime.of(15,59,12);
		Duration diff_by_duration  = Duration.between(former_lotime, now_lotime);
		System.out.printf("\t现在时间=%s,过去时间=%s,差值=%s\n",former_lotime,now_lotime,diff_by_duration);
		
		
		
		logger.info("JAVA8.. 时区转换");
		ZoneId am_zone = ZoneId.of("America/New_York");
		LocalDateTime lodate_time = LocalDateTime.now();
		ZonedDateTime amdate_time =ZonedDateTime.of(lodate_time, am_zone);
		System.out.printf("\t本地时间=%s,美国时间=%s\n",lodate_time,amdate_time);
		
		logger.info("JAVA8.. 包含时差信息的日期时间");
		LocalDateTime lodatetime = LocalDateTime.of(2020,3,1,17,0);
		ZoneOffset offset = ZoneOffset.of("+05:30");
		OffsetDateTime offset_d = OffsetDateTime.of(lodatetime, offset);
		System.out.printf("\t包含时差的日期时间=%s\n",offset_d);
		
		
		logger.info("JAVA8.. 日期<-->String格式化");
		String dateString = "2019-12-10";
		LocalDate date_from_str = LocalDate.parse(dateString);
		System.out.printf("\tLocalDate.parse(dateString)=%s\n",date_from_str);
		
		String dateString2 = "20191210";
		LocalDate date_from_str2 = LocalDate.parse(dateString2,DateTimeFormatter.BASIC_ISO_DATE);
		System.out.printf("\tLocalDate.parse(dateString)=%s\n",date_from_str2);
		
		String dateString3 = "Apr 18 2014";
		DateTimeFormatter self_format = DateTimeFormatter.ofPattern("MMM dd yyyy");
//		LocalDate holiday = LocalDate.parse(dateString3,self_format);
//		System.out.printf("\tLocalDate.parse()=%s\n",holiday);
		
		DateTimeFormatter self_format2 = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
		LocalDateTime festival = LocalDateTime.now();
		String festival_res =  festival.format(self_format2);
		System.out.printf("\tLocalDateTime.format()=%s\n",festival_res);
		
	}

}
