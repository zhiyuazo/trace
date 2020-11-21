package com.zzy.trace.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class test {
	public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            // 连接数据库how2j.db,不用手动创建。。。
            Connection connection = DriverManager.getConnection("jdbc:sqlite:sqliteTest.db");
            Statement statement = connection.createStatement();
            statement.executeUpdate("drop table if exists hero");
            statement.executeUpdate("create table hero(id int,name varchar(20),hp int)");
            statement.executeUpdate("insert into hero values(1,'Gareen','452')");
            ResultSet rSet = statement.executeQuery("select * from hero");
            while (rSet.next()) { 
                System.out.println("id：" + rSet.getInt(1));
                System.out.println("姓名：" + rSet.getString(2));
                System.out.println("血量：" + rSet.getString(3));
            }
            rSet.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        }
	}

}
