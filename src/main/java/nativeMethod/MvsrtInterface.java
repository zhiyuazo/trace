package nativeMethod;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
/*
* DLL动态库调用方法
* @Description: 读取调用CDecl方式导出的DLL动态库方法
* @author:	ZZY 
*/
//定义一个接口，继承自Library || StdCallLibrary(DLL中为stdcall方式输出的
//原文链接：https://blog.csdn.net/gwd1154978352/article/details/55097376/
/*
第一个参数是动态链接库dll/so的名称，
	a.但不带.dll或.so这样的后缀，这符合JNI的规范，因为带了后缀名就不可以跨操作系统平台了。
	b.搜索动态链接库路径的顺序是：
		1.先从当前类的当前文件夹找，
		2.在工程当前文件夹下面找win32/win64文件夹，找到后搜索对应的dll文件，
		3.再到WINDOWS下面去搜索，
		4. 1-3找不到抛异常。比如上例中printf函数在Windows平台下所在的dll库名称是msvcrt，而在其它平台如Linux下的so库名称是c。
第二个参数是本接口的Class类型。
	a.JNA通过这个Class类型，根据指定的.dll/.so文件，动态创建接口的实例。
	b.该实例由JNA通过反射自动生成。
*/
public interface MvsrtInterface extends Library {
   String dllname = Platform.isWindows() ? "msvcrt" : "c";
   MvsrtInterface INSTANCE = (MvsrtInterface) Native.loadLibrary(dllname,MvsrtInterface.class);
   // 声明将要调用的DLL中的方法,可以是多个方法(此处示例调用本地动态库msvcrt.dll中的printf()方法)
   void printf(String format, Object... args);
   int toupper(int ch);
   double pow(double x,double y);
}