package com.zzy.trace.db;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtilsPlus{
	public static void executeInsert(String sql , Object o1 , Object o2) {
	}
	public static void executeInsert(String sql , Object o1 , Object o2,Object o3) {
	}
	public static void executeInsert(String sql , Object o1 , Object o2,Object o3,Object o4) {
	}
	public static String getTableSql(Class c , String tname) throws SQLException {
		String preSQL = "DROP TABLE IF EXISTS " + tname + ";\n";
		String headSQL = "CREATE TABLE \"public\".\"" + tname + "\"(" + "\n" ;
		String idSQL = "\t\"" +"id" + "\"" + "  " + "serial8,\n"; 
		String tailSQL = ");\n";
		ArrayList<String> subSQLs = new ArrayList<>();
		
		Field[] fs = c.getFields();
		for(Field f : fs) {
			String type = f.getType().getSimpleName();
			String subsql = null;
			switch (type) {
				case "char":
					subsql = "\t\"" +  f.getName().trim() + "\""  + "  " + "text" ;
					subSQLs.add(subsql);
					break;
				case "char[]":
					subsql = "\t\"" +  f.getName().trim() + "\""  + "  " + "text" ;
					subSQLs.add(subsql);
					break;
				case "byte":
					subsql = "\t\"" +  f.getName().trim() + "\""  + "  " + "int" ;
					subSQLs.add(subsql);
					break;
				case "byte[]":
					subsql = "\t\"" +  f.getName().trim() + "\""  + "  "+ "text" ;
					subSQLs.add(subsql);
					break;
				case "short":
					subsql = "\t\"" +  f.getName().trim() + "\""  + "  " + "int2" ;
					subSQLs.add(subsql);
					break;
				case "int":
					subsql = "\t\"" +  f.getName().trim() + "\""  + "  " + "int4" ;
					subSQLs.add(subsql);
					break;
				case "long":
					subsql = "\t\"" +  f.getName().trim() + "\""  + "  " + "int8" ;
					subSQLs.add(subsql);
					break;
				case "double":
					subsql = "\t\"" +  f.getName().trim() + "\""  + "  " + "float8" ;
					subSQLs.add(subsql);
					break;
				case "float":
					subsql = "\t\"" +  f.getName().trim() + "\""  + "  "  + "float4" ;
					subSQLs.add(subsql);
					break;
				case "String":
					subsql = "\t\" " +  f.getName().trim() + "\" "  + "  " + "text" ;
					subSQLs.add(subsql);
					break;
				case "Boolean|boolean":
					subsql = "\t\" " +  f.getName().trim() + "\" "  + "  " + "bool" ;
					subSQLs.add(subsql);
					break;
				case "LocalTime":
					subsql = "\t\" " +  f.getName().trim() + "\" "  + "  " + "time" ;
					subSQLs.add(subsql);
					break;
				case "Timestamp|LocalDateTime|OffsetDateTime":
					subsql = "\t\" " +  f.getName().trim() + "\" "  + "  " + "timestamp" ;
					subSQLs.add(subsql);
					break;
				case "LocalDate|Date":
					subsql = "\t\" " +  f.getName().trim() + "\" "  + "  " + "date" ;
					subSQLs.add(subsql);
					break;
				default:
					subsql = "\t\"" +  f.getName().trim() + "\""  + "  " + "text" ;
					subSQLs.add(subsql);
					break;
			}
		}
//		String bodySQL = Strings.join(subSQLs, ",\n");
//		String full_sql  =  preSQL + headSQL + idSQL + bodySQL  + tailSQL;
//		return full_sql;
		return null;
	}
	
	public static ArrayList<String> fetchTableCols(String tname) throws SQLException {
	//Connection just a example, in real DEV,should with a DBconnection-Pool..
	Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306");
		String sql = "select column_name from information_schema.columns WHERE table_schema='public' and table_name='" + tname + "'";
		Statement stat = null;
		ResultSet rs = null ;
		ArrayList<String> res = new ArrayList<String>();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()) {
				String tmp = rs.getString(1);
				res.add(tmp);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			conn.close();
		}
		return res;
	}
	
	public static boolean saveObject(String tname , Object obj ) throws SQLException {
		//Connection just a example, in real DEV,should with a DBconnection-Pool..
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306");
		if (obj == null) {
			return false;
		}
		
		Field[] fs = obj.getClass().getFields();
		int paramCount =  fs.length ; 
		//如果包含id 键值，则不赋值，另其在DB中自增长，否则id号不增长
		for(Field f : fs) {
			if(f.getName().equals("id")) {
				paramCount= fs.length -1 ;
				break;
			}
		}
		
		String valueStr = "";
		for (int i = 0; i < paramCount; i++) {
			valueStr = valueStr.concat("?,");
		}
		valueStr = valueStr.substring(0, valueStr.length() - 1);
		
		Object [] objs = new Object[paramCount] ;
		int i = 0;
		String columnStr = "";
		for (Field f : fs) {
			try {
				if(! f.getName().equals("id")) {
					Object o = f.get(obj);
					columnStr = columnStr.concat("\"" +f.getName() +"\"" + ",");
					objs[i] = o;
					i++;
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		columnStr = columnStr.substring(0, columnStr.length() - 1);
		String sql = "insert into " + tname+ "(" + columnStr + ") values(" + valueStr + ")";
		System.out.println(sql);
		return true;
	}
	
	public static List<Object> list(String tname ,int start, int count, Class c) throws SQLException {
		//Connection just a example, in real DEV,should with a DBconnection-Pool..
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306");
		List<Object> result = new ArrayList<>();
		String sql = "select * from " + tname +  " order by id desc offset ? limit ?";
		try(Connection con = conn;PreparedStatement  ps = con.prepareStatement(sql);) {
			Field[] fs = c.getFields();
			ps.setInt(1, start);
			ps.setInt(2, count);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				Object o = c.newInstance();
				for(Field f : fs ) {
					String attr = f.getName();
					if(rs.getObject(attr) != null) {
						f.set(o, rs.getObject(attr));
					}
				}
				result.add(o);
			}
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static List<Object> listByTime(String tname ,long time_start ,long time_end ,int offset, int limit, Class c) throws SQLException {
		//Connection just a example, in real DEV,should with a DBconnection-Pool..
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306");
		List<Object> result = new ArrayList<>();
		String sql = "select * from " + tname +  " where " + " \"TimeOfCollection\" " + " between ? and ?  order by id desc  offset ? limit ?";
		try(Connection con =conn; PreparedStatement ps = con.prepareStatement(sql);) {
			Field[] fs = c.getFields();
			ps.setLong(1, time_start);
			ps.setLong(2, time_end);
			ps.setInt(3, offset);
			ps.setInt(4, limit);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				Object o = c.newInstance();
				for(Field f : fs ) {
					String attr = f.getName();
					if(rs.getObject(attr) != null) {
						f.set(o, rs.getObject(attr));
					}
				}
				result.add(o);
			}
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static List<Object> listByTime(String tname ,long time_start ,long time_end ,Class c) throws SQLException {
		//Connection just a example, in real DEV,should with a DBconnection-Pool..
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306");
		List<Object> result = new ArrayList<>();
		String sql = "select * from " + tname +  " where " + " \"TimeOfCollection\" " + " between ? and ?  order by id desc";
		try(Connection con = conn;PreparedStatement  ps = con.prepareStatement(sql);) {
			Field[] fs = c.getFields();
			ps.setLong(1, time_start);
			ps.setLong(2, time_end);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				Object o = c.newInstance();
				for(Field f : fs ) {
					String attr = f.getName();
					if(rs.getObject(attr) != null) {
						f.set(o, rs.getObject(attr));
					}
				}
				result.add(o);
			}
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}
}