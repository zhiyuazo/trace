package com.zzy.trace.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zzy.trace.properties.PropertiesUtils;


/**
 * jdbc连接池 使用dbcp连接池
 * 
 * @author zzy
 */
public class DBCPool {
	private static final Logger logger = LoggerFactory.getLogger(DBCPool.class);

	//mariaDB数据库
	private static final String configfile = "dbcp_jdbc";
	private static DataSource dataSource = null;

	public DBCPool() {
		
	}
	
	/**
	 * 初始化datasource,采用同步方法,防止多线程并发初始化datasource
	 */
	private static synchronized void initDataSource() {
		if (dataSource == null) {
			try {
				dataSource = BasicDataSourceFactory.createDataSource(loadConfig());
				logger.info("JDBC Connection Pool Create Finish.");
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	private static synchronized void initDataSource(String host,String username,String password,String port,String database) {
		if (dataSource == null) {
			try {
				dataSource = BasicDataSourceFactory.createDataSource(loadConfig(host,username,password,port,database));
				logger.info("JDBC Connection Pool Create Finish.");
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 初始化datasource,采用同步方法,防止多线程并发初始化datasource
	 */
	public static synchronized void initDataSource(Properties properties) {
		if (dataSource == null) {
			Properties props = loadConfig();
			props.putAll(properties);
			try {
				dataSource = BasicDataSourceFactory.createDataSource(props);
				logger.info("JDBC Connection Pool Create Finish.");
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 加载jdbc配置文件。1.系统属性jdbc.config.path加载制定配置文件，2.查找资源文件jdbc.properties
	 * @return
	 */
	private static Properties loadConfig() {
		Properties prop = new Properties();
		String path = System.getProperty("jdbc.config.path");

		if (path == null) {
			prop = PropertiesUtils.loadProperties(configfile);
		} else {
			try {
				prop.load(new FileInputStream(path));
				logger.info("Load {} file. Info : {}", path, prop.toString());
			} catch (IOException e) {
				String msg = "please set 'jdbc.properties' file path param  eg: -Djdbc.config.path=/xxx/jdbc.properties";
				logger.error("load jdbc config '{}' file error! " + msg, path);
				throw new RuntimeException(e);
			}
		}

		return prop;
	}
	/**
	 * 加载jdbc配置文件。1.系统属性jdbc.config.path加载制定配置文件，2.查找资源文件jdbc.properties
	 * @param host 读取联排模式中的host
	 * @param username 读取联排模式中的username
	 * @param password 读取联排模式中的password
	 * @param port 读取联排模式中的port
	 * @param database 读取联排模式中的database
	 * @return
	 */
	private static Properties loadConfig(String host,String username,String password,String port,String database){
		Properties prop = new Properties();
		String path = System.getProperty("jdbc.config.path");

		if (path == null) {
			prop = PropertiesUtils.loadProperties(configfile);
			prop.setProperty("url", "jdbc:postgresql://"+host+":"+port+"/"+database);
			prop.setProperty("username",username);
			prop.setProperty("password",password);
		} else {
			try {
				prop.load(new FileInputStream(path));
				logger.info("Load {} file. Info : {}", path, prop.toString());
			} catch (IOException e) {
				String msg = "please set 'jdbc.properties' file path param  eg: -Djdbc.config.path=/xxx/jdbc.properties";
				logger.error("load jdbc config '{}' file error! " + msg, path);
				throw new RuntimeException(e);
			}
		}

		return prop;
	}

	/**
	 * 获取DataSource
	 * @return
	 */
	public static DataSource getDataSource() {
		if (dataSource == null) {
			initDataSource();
		}

		return dataSource;
	}
	
	/**
	 * 获取联排模式下的DataSource
	 * @return
	 */
	public static DataSource getDataSource(String host,String username,String password,String port,String database) {
		if (dataSource == null) {
			initDataSource(host,username,password,port,database);
		}

		return dataSource;
	}

	/**
	 * 连接池中获取jdbc连接
	 * @return
	 */
	public static final Connection getConnection() {
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return conn;
	}
	
	/**
	 * 联排模式中连接池中获取jdbc连接
	 * @return
	 */
	public static final Connection getConnection(String host,String username,String password,String port,String database) {
		Connection conn = null;
		try {
			conn = getDataSource(host,username,password,port,database).getConnection();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return conn;
	}
	
	public static void main(String[] args) {
    	String sql = "select * from t1";
    	try(Connection  conn = DBCPool.getConnection() ;
    		Statement s = conn.createStatement();
    	){
    		ResultSet rs = s.executeQuery(sql);
    		while(rs.next()) {
    			System.out.println(rs.getString(1));
    		}
    	}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
