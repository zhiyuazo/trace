package nativeMethod;

public class JOriginal {
	
	public static void main(String[] args) throws InterruptedException {
		long s = System.currentTimeMillis();
    	for(int i=0 ; i< 10 * 10000 ;i++) {
    		double res = Math.pow(10.645, 12.358);
    	}
		long e = System.currentTimeMillis();
		System.out.println((e-s));
	}
	
}
