package testClass;

import java.lang.reflect.Field;

import testModel.father;

public class testGetFields {
	public static void main(String[] args) {
		father  sfr = new father();
		Class cls = sfr.getClass();
		Field[] fs  = cls.getFields();
		int i = 0;
		for(Field f: fs) {
			i++;
			System.out.println(f.getName());
		}
		System.out.println("Total: " +i);
		
	}

}
