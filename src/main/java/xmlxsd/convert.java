package xmlxsd;

import java.io.File;

import javax.xml.bind.JAXB;

public class convert {
	
	public static void xsd2java() {
		/*
		 * 方法一： xjc JAVA 自带命令 ,最好带上编码...否则乱码....
			xjc -encoding "UTF-8" -d src/main/java/xmlxsd/ -p xmlxsd src/main/java/xmlxsd/example.xsd
			-d指定生成的java文件存放的磁盘目录
			-p指定生成的java文件的包名
			最后要指定XSD文件的路径，如果指定的XSD的依赖其它的XSD,所依赖的XSD必须与指定的XSD在同一个目录中
			
		方法二：解决xjc不能编码的问题,,但是上面java1.8版本上，带上编码参数是没有问题的，下面这个命令找不到类...
			java -Dfile.encoding=UTF-8 -cp %JAVA_HOME%\lib\tools.jar com.sun.tools.internal.xjc.Driver  -p xmlxsd   src/main/java/xmlxsd/example.xsd
		方法三：Eclipse (未测试)
			new --> other--> JAXB --> JAXB Classes from Schema
		*/
	}
	public static void java2xsd() {
		/*
			方法一：Eclipse (未测试)
				new --> other--> JAXB --> Schema from JAXB Classes
		*/
	}
	
	public static void java2xml() {
        xmlxsd.data.java2xml tmp = new xmlxsd.data.java2xml("abc", "男", "北京", "朝阳区");
        File file = new File("java2xml.xml");
        JAXB.marshal(tmp, file);
	}
	
	public static void xml2java() {
        File file = new File("java2xml.xml");
        xmlxsd.data.java2xml tmp = JAXB.unmarshal(file, xmlxsd.data.java2xml.class);
        System.out.println(tmp);
	}
	
	public static void xml2xsd() {
		/**
		 * trang.jar 解决
		 * java -jar trang20181222.jar -I xml -O xsd  data/xml2xsd.xml ./result.xsd
		 */
	}
	
	public static void xsd2xml() {
		/**
		 * skip, not reasonable
		 */
	}
	
	
	public static void main(String[] args) {
		convert.java2xml();
		convert.xml2java();
	}

}
