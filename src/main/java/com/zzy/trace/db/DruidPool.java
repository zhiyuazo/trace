package com.zzy.trace.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.zzy.trace.properties.PropertiesUtils;

public class DruidPool {
	public static DruidDataSource  dataSource=null;
	public static DataSource  dataSource1=null; //两种方式都OK了
	private DruidPool(){
	}
	//获得数据源(连接池)
    public static DataSource getDataSource() throws Exception {
//    	if(dataSource == null) {
//    		dataSource = new DruidDataSource();
//    		dataSource.setUrl("jdbc:mysql://www.coolidea.store:3306/test");
//    		dataSource.setUsername("zzy");
//    		dataSource.setPassword("zzy@ali");
//    		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//    	}
    	
    	if(dataSource1 == null) {
			Properties pp = PropertiesUtils.loadProperties("druid_jdbc");
			
	        InputStream ins = DruidPool.class.getClassLoader().getResourceAsStream("druid_jdbc.properties");
	        pp.load(ins);
			System.out.println(pp.toString());
    		dataSource1 = DruidDataSourceFactory.createDataSource(pp);
    	}
        return dataSource1;
    }
    //获得连接
    public static Connection getConnection() {
        try {
        	DataSource ds = DruidPool.getDataSource();
            return ds.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, SQLException {
    	String sql = "select * from t1";
    	Connection  conn = DruidPool.getConnection();
    	Statement s = conn.createStatement();
    	try{
    		ResultSet rs = s.executeQuery(sql);
    		while(rs.next()) {
    			System.out.println(rs.getString(1));
    		}
    	}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
