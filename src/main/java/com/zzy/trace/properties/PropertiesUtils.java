package com.zzy.trace.properties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 配置文件工具类 对.properties配置文件的读取操作 配置文件的加载,根据文件名(不带后缀.properties)加载配置文件
 * 配置文件在加载过一次后,后续的配置读取不做加载
 * 
 * @author hhm
 *
 */
public class PropertiesUtils {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

	// 缓存加载的.properties配置文件
	private static Map<String, Properties> propMap = new HashMap<String, Properties>();

	/**
	 * 读取配置文件中属性,不存在则返回默认值
	 * 
	 * @param filename
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getProperty(String filename, String key, String defaultValue) {
		String val = defaultValue;

		Properties p = loadProperties(filename);
		if (p != null) {
			val = p.getProperty(key, defaultValue);
		}

		return val;
	}

	/**
	 * 修改配置文件
	 * 
	 * @param filename
	 *            文件名，不带.propertires
	 * @param key
	 * @param value
	 * @param comment
	 *            注释
	 * @return
	 * @throws IOException
	 */
	public static void setProperty(String filename, String key, String value, String comment) throws IOException {

		Properties p = loadProperties(filename);

		OutputStream out = null;
		try {
			out = new FileOutputStream(new File("")/*need alter*/);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		p.put(key, value);
		p.store(out, comment);
		out.close();
	}

	public static String getPropertyNullException(String filename, String key) {
		String val = getProperty(filename, key);
		if (val == null) {
			throw new RuntimeException("\"" + key + "\" property is null! Set in file \"" + filename + "\".properties");
		}
		return val;
	}

	/**
	 * 读取配置文件中属性,无则返回null
	 * 
	 * @param filename
	 *            文件名(不带后缀.properties)
	 * @param key
	 * @return 属性不存在则返回 null
	 */
	public static String getProperty(String filename, String key) {
		return getProperty(filename, key, null);
	}

	/**
	 * 读取配置文件中string属性
	 * 
	 * @param filename
	 *            文件名(不带后缀.properties)
	 * @param key
	 * @return 属性不存在则返回 ""
	 */
	public static String getString(String filename, String key) {
		return getProperty(filename, key, "");
	}

	/**
	 * 读取配置文件中string属性，指定默认值
	 * 
	 * @param filename
	 *            文件名(不带后缀.properties)
	 * @param key
	 * @param defValue
	 *            默认值
	 * @return
	 */
	public static String getString(String filename, String key, String defValue) {
		return getProperty(filename, key, defValue);
	}

	/**
	 * 读取配置文件中int属性，默认0
	 * 
	 * @param filename
	 *            文件名(不带后缀.properties)
	 * @param key
	 * @return
	 */
	public static int getInt(String filename, String key) {
		return (int) getLong(filename, key);
	}

	/**
	 * 读取配置文件中int属性，指定默认值
	 * 
	 * @param filename
	 *            文件名(不带后缀.properties)
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static int getInt(String filename, String key, int defValue) {
		return (int) getLong(filename, key, defValue);
	}

	/**
	 * 读取配置文件中long属性，默认0
	 * 
	 * @param filename
	 *            文件名(不带后缀.properties)
	 * @param key
	 * @return
	 */
	public static long getLong(String filename, String key, long defValue) {
		String s = getProperty(filename, key, String.valueOf(defValue));
		return Long.parseLong(s);
	}

	/**
	 * 读取配置文件中long属性，指定默认值
	 * 
	 * @param filename
	 *            文件名(不带后缀.properties)
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static long getLong(String filename, String key) {
		String s = getProperty(filename, key, "0");
		return Long.parseLong(s);
	}

	/**
	 * 加载配置文件
	 * 
	 * @param filename
	 *            文件名(不带后缀.properties)
	 * @return
	 */
	public final static Properties loadProperties(String filename) {
		String fname = filename + ".properties";
		Properties p = null;
		InputStream is = null;
		if (propMap.containsKey(filename)) {
			p = propMap.get(filename);
		} else {
			try {
				p = new Properties();
				is = null; //need alter
				if (is != null) {
					p.load(new InputStreamReader(is, "UTF8"));
				}
				propMap.put(filename, p);
				logger.info("Properties file '" + fname + "' content : " + p.toString());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new RuntimeException("Properties file '" + fname + "' Load Exception!\n" + e.getMessage());
			} finally {
				try {
					if (is != null) {
						is.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return p;
	}
}
