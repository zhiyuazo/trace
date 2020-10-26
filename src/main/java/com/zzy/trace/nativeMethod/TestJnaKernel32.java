package com.zzy.trace.nativeMethod;

/**
 * JNA框架DLL动态库读取调用示例类
 * @ClassName: DllCall
 * @Description: 读取调用DLL动态库文件中的方法
 * @author: ZZY
 * @date: 2018年7月18日 上午10:32:16
 */
public class TestJnaKernel32 {
	public static Kernel32Interface inc = Kernel32Interface.INSTANCE;
    public static void main(String[] args) {
    	Kernel32Interface.SYSTEMTIME time = new Kernel32Interface.SYSTEMTIME ();
        inc.GetLocalTime (time);
        System.out.println ("Year is "+time.wYear);
        System.out.println ("Month is "+time.wMonth);
        System.out.println ("Day of Week is "+time.wDayOfWeek);
        System.out.println ("Day is "+time.wDay);
        System.out.println ("Hour is "+time.wHour);
        System.out.println ("Minute is "+time.wMinute);
        System.out.println ("Second is "+time.wSecond);
        System.out.println ("Milliseconds are "+time.wMilliseconds);
    }
}