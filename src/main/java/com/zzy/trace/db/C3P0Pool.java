package com.zzy.trace.db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.management.RuntimeErrorException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
public class C3P0Pool {
//    public static ComboPooledDataSource dataSource=new ComboPooledDataSource("lqg");
    public static ComboPooledDataSource dataSource=new ComboPooledDataSource();
    
	//获得数据源(连接池)
    public static DataSource getDataSource() {
        return dataSource;
    }
    //获得连接
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
    	String sql = "select * from t1";
    	try(Connection  conn = C3P0Pool.getConnection() ;
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
