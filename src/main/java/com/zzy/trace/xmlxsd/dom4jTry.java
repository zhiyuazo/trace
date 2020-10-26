package com.zzy.trace.xmlxsd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class dom4jTry {
	
	
	
	public static void main(String[] args) throws IOException, DocumentException {
		dom4jTry.createXMLfileDemo();
		dom4jTry.doc2String();
		dom4jTry.string2Doc();
		dom4jTry.readXml2Doc_sax();
		
	}
	
    public static void readXml2Doc_sax() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("dom4jread.xml"));
        Element root = document.getRootElement();

        // 遍历子元素
        for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
            Element element = it.next();
            System.out.println(element.getName());
        }
        // 遍历指定的子元素
        for (Iterator<Element> it = root.elementIterator("author"); it.hasNext();) {
            Element author = it.next();
            System.out.println(author.getName());
        }
        // 遍历属性
        for (Iterator<Attribute> it = root.attributeIterator(); it.hasNext();) {
            Attribute attribute = it.next();
            System.out.println(attribute.getName());
        }
        
        // 快速遍历,避免创建迭代器
        for (int i = 0, size = root.nodeCount(); i < size; i++) {
            Node node = root.node(i);
            System.out.println(node.getName());
        }
    }
    public static void readXml2Doc_dom() throws DocumentException {
//        DOMReader reader = new DOMReader();
//        Document document = reader.read(domDocument)
//    	Element root = document.getRootElement();
//    	System.out.println(root.getName());
    }
    public static void string2Doc() throws DocumentException {
    	String text = "<person> <name>James</name> </person>";
    	Document document = DocumentHelper.parseText(text);
    	Element root = document.getRootElement();
    	System.out.println(root.getName());
    }
    
    public static void doc2String() {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");
        Element author1 = root.addElement("author")
            .addAttribute("name", "James")
            .addAttribute("location", "UK")
            .addText("James Strachan");

        Element author2 = root.addElement("author")
            .addAttribute("name", "Bob")
            .addAttribute("location", "US")
            .addText("Bob McWhirter");
        
        String res = document.asXML();
        System.out.println(res);
    }
    
    //Create XML then write to a file....
    public static void createXMLfileDemo() throws IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");
        Element author1 = root.addElement("author")
            .addAttribute("name", "James")
            .addAttribute("location", "UK")
            .addText("James Strachan");

        Element author2 = root.addElement("author")
            .addAttribute("name", "Bob")
            .addAttribute("location", "US")
            .addText("Bob McWhirter");
    	
        //写方法零，document.write方法 notepad 下格式不好阅读（紧凑）
        FileWriter xmlfile = new FileWriter("xmlfile.xml");
        document.write(xmlfile);
        xmlfile.close();
        
        
        //写方法一， XMLwrite 直接写文件notepad 下格式不好阅读（紧凑）
        FileWriter xmlfile_1 = new FileWriter("xmlfile_1.xml");
        XMLWriter xmlwriter_1 = new XMLWriter(xmlfile_1);
        xmlwriter_1.write( document );
        xmlwriter_1.close();


        //写方法二， XMLwrite 优雅的格式输出到 文件|System.out
        FileWriter xmlfile_2 = new FileWriter("xmlfile_2.xml");
        OutputFormat format_pretty = OutputFormat.createPrettyPrint();
//        XMLWriter xmlwriter_2 = new XMLWriter(System.out, format);
        XMLWriter xmlwriter_2 = new XMLWriter(xmlfile_2, format_pretty);
        xmlwriter_2.write( document );
        xmlwriter_2.close();

        //写方法三， XMLwrite 紧凑格式输出到  文件|System.out
        FileWriter xmlfile_3 = new FileWriter("xmlfile_3.xml");
        OutputFormat format_compact = OutputFormat.createCompactFormat();
//        XMLWriter xmlwriter_3 = new XMLWriter(System.out, format_compact);
        XMLWriter xmlwriter_3 = new XMLWriter(xmlfile_3, format_compact);
        xmlwriter_3.write(document);
        xmlwriter_3.close();
        
        //写方法四， XMLwrite OutputStreamWriter,紧凑格式输出到  文件
        FileOutputStream fos = new FileOutputStream("xmlfile_4.xml");
        OutputStreamWriter os = new OutputStreamWriter(fos, "UTF-8");
        XMLWriter xmlwriter_4 = new XMLWriter(os);
        xmlwriter_4.write(document);  
        xmlwriter_4.close();  
    }
    
}
