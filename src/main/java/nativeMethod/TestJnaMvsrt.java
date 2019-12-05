package nativeMethod;

/**
 * JNA框架DLL动态库读取调用示例类
 * @ClassName: DllCall
 * @Description: 读取调用DLL动态库文件中的方法
 * @author: ZZY
 * @date: 2018年7月18日 上午10:32:16
 */
public class TestJnaMvsrt {
	public static MvsrtInterface inc = MvsrtInterface.INSTANCE;
    public static void main(String[] args) {
    	long s = System.currentTimeMillis();
    	for(int i=0 ; i< 10 * 10000;i++) {
			double res = inc.pow(10.645, 12.358);
//			inc.printf("Hello, Worldn");
//			char a = (char)inc.toupper((int)'a');
    	}
		long e = System.currentTimeMillis();
		System.out.println((e-s));
    }
}