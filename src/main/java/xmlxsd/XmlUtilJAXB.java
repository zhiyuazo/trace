package xmlxsd;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.xml.bind.JAXB;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

/**
 * XML工具类
 * @author yt(Who is)
 * 2019年10月01日
 */
public class XmlUtilJAXB {	
	private static final Logger logger = LoggerFactory.getLogger(XmlUtilJAXB.class);
	/**
	 * xml序列化
	 * @param obj 待序列化的对象
	 * @return
	 */
	public static String objToXml(Object obj) {
		StringWriter xmlWrite = new StringWriter();

		JAXB.marshal(obj, xmlWrite);
		return xmlWrite.toString();
	}
	
	/**
	 * xml反序列化
	 * @param xml 需反序列化的xml字符串
	 * @param type 反序列化之后的类型
	 * @return
	 */
	public static <T> T xmlToObj(String xml, Class<T> type) {
		return JAXB.unmarshal(new StringReader(xml), type);
	}

	/**
	 * 使用xsd验证xml
	 * @param xmlIS 待验证的xml内容
	 * @param xsdIS 用于验证的xsd内容
	 * @return
	 */
	public static boolean validate(InputStream xmlIS, InputStream xsdIS) {
		SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");		
		
		try {			
			Schema schema = sf.newSchema(new StreamSource(xsdIS));			
			Validator validator = schema.newValidator();			
			Source source = new StreamSource(xmlIS);			
			validator.validate(source);
			
			return true;
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 压缩xml
	 * @param xml 待压缩的xml内容
	 * @param encoding 编码
	 * @return
	 */
	public static String toCompactFormat(String xml, String encoding) {
		InputStream is = null;

		try {
			is = new ByteArrayInputStream(xml.getBytes(encoding));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		OutputFormat format = OutputFormat.createCompactFormat();
		format.setEncoding(encoding);

		return toCompactFormat(is, encoding);
	}

	/**
	 * 压缩xml
	 * @param is 待压缩的xml内容
	 * @param encoding 编码
	 * @return
	 */
	public static String toCompactFormat(InputStream is, String encoding) {
		OutputFormat format = OutputFormat.createCompactFormat();
		format.setEncoding(encoding);

		return convertFormat(is, format);
	}

	/**
	 * 将xml转换成美观的可读形式
	 * @param xml 待压缩的xml内容
	 * @param encoding 编码
	 * @return
	 */
	public static String toPrettyFormat(String xml, String encoding) {
		InputStream is = null;

		try {
			is = new ByteArrayInputStream(xml.getBytes(encoding));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(encoding);

		return toPrettyFormat(is, encoding);
	}

	/**
	 * 将xml转换成美观的可读形式
	 * @param is 待压缩的xml内容
	 * @param encoding 编码
	 * @return
	 */
	public static String toPrettyFormat(InputStream is, String encoding) {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(encoding);

		return convertFormat(is, format);
	}

	private static String convertFormat(InputStream is, OutputFormat format) {
		String convertedXml = null;

		SAXReader saxReader = new SAXReader();

		try{
			Document document = saxReader.read(is);

			StringWriter sw = new StringWriter();
			XMLWriter writer = new XMLWriter(sw, format);
			writer.write(document);
			convertedXml = sw.toString();

			writer.close();
			sw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return convertedXml;
	}
}
