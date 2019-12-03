package NativeMethod;

public class joriginal {
	
	public static void main(String[] args) throws InterruptedException {
		joriginal  ins  = new joriginal();
		long start = System.currentTimeMillis();
		for (int i = 0 ; i < 1000 * 10000;i++) {
			int res = ins.computeSum(i, i+1);
		}
		long end = System.currentTimeMillis();
		System.out.println("cost: " + (end -start));
	}
	
	public  int computeSum(int a, int b) {
		int res = a + b ;
		return res; 
	}
}
