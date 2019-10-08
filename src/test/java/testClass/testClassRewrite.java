package testClass;

import testModel.fatherRewrite;
import testModel.sonRewrite;

public class testClassRewrite {
	
	public static void main(String[] args) {
		fatherRewrite s = new sonRewrite("a");
		sonRewrite s2 = new sonRewrite("b");
		System.out.println(s.table);
		System.out.println(s2.table);
	}
}
