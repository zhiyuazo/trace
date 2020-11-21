package com.zzy.trace.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Code4DB {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		/**
		数据库端启动：
		A. memory only 模式 ， 存储在内存中。随程序结束而结束
		       skip...用于内存中交换数据???
		B. servlet 模式 ，类似web-server方式
		       skip...
		C. web-server 模式 ，默认端口9001
			  java -cp hsqldb.jar org.hsqldb.WebServer -database.0 db/mydb -dbname.0 xdb
		D. server 模式
			  java -cp hsqldb.jar org.hsqldb.Server -database.0 db/mydb -dbname.0 xdb
			  ①将会在db文件夹下创建一个数据库mydb
			  ②xdb相当于数据库别名,用于访问数据库
		E. standalone模式  同一时刻only-one进程可以使用
			数据文件存储在windows-用户下
		客户端启动       
		1. java  -cp  hsqldb-2.2.9.jar   org.hsqldb.util.DatabaseManagerSwing
		2. java  -cp  hsqldb-2.2.9.jar   org.hsqldb.util.DatabaseManager
		**/
        Class.forName("org.hsqldb.jdbcDriver");
        String url_standalone = "jdbc:hsqldb:file:D:\\JAVAWorkSpace\\trace\\src\\main\\java\\com\\zzy\\trace\\hsqldb\\standalone\\mydbfile;shutdown=true";
        String url_server = "jdbc:hsqldb:hsql://localhost/mydb";
        Connection c =DriverManager.getConnection(url_standalone, "SA", "");
        Statement st = c.createStatement();
        ResultSet rs= st.executeQuery("select * from category");
        while(rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println(String.format("id=%s-name=%s", id,name));
        }
        st.close();
        c.close();
		
	}
}
