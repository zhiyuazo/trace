package nativeMethod;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.util.User32;  
  
/*
 *  jnative在官网中 说明：只支持32位的windows 2000/xp/2003,linux操作系统。
	要在64位的操作系统中使用jnative 需要安装32位的jdk ，不能用64位的jdk。而且jnative所要调用的dll，也应该是32位系统中编译的。
	目前jnative最高位1.4版，而且好像不再更新了。
 */
public class TestJNative extends org.xvolks.jnative.util.Kernel32.SystemTime {  
    public TestJNative() throws NativeException {
		super();
	}

	public static void main(String[] args) throws Exception {
        System.loadLibrary("JNativeCpp");
        User32.MessageBox(0, "JNative的确很酷哦，可惜版本太老了", 
                "JNative 演示程序", 0);
        System.out.println(JNative.isWindows());

    }
}  