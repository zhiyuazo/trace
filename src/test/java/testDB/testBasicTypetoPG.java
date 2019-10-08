package testDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import testModel.testSQLs;
//本质上:
//1. setXX 进入数据库   getXX 取到数据...不会有什么问题，数据库字段类型定义好，即可。
//2. setObject 进入数据库   getXX 取到数据...不会有什么问题， 因为set/getXX已经包含类型转换
//3. setXX 进入数据库   getObject 取到数据:
//		同下
//4. setObject 进入数据库   getObject 取到数据:
//        java-type | 数据库类型  | Select返回类型
//--------------------------------------       
//		则  boolean --->bool ---> Boolean
//		  short   --->int2 ---> Integer 
//		  int   --->  int4 ---> Integer 
//		  long    --->int8 ---> Long
//		  float   --->float4--> Float
//		  double  --->float8--> Double
//		  String  --->text ---> String
//		  char --->[char,char(x),varchar] --->String(in PG)
//		  byte --->   int2  --->  Integer
//		  byte[] ---> bytea --->  bytea
//		  json   ---> text  --->  String
//        Object ---> bytea --->  byte[]  
public class testBasicTypetoPG {
		public static void main(String[] args) throws SQLException {
			//INSERT
			try(Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306");PreparedStatement  ps = con.prepareStatement(testSQLs.sql_insert_basic);) {
				boolean obj = false;
				if(obj) {
					ps.setObject(1, false);
					ps.setObject(2, (short) 65537);
					ps.setObject(3, 888);
					ps.setObject(4, 999L);
					ps.setObject(5, 777f);
					ps.setObject(6, 999.9999);
					ps.setObject(7, "acdefg");
					ps.execute();
				}else {
					ps.setBoolean(1, false);
					ps.setShort(2, (short) 65537);
					ps.setInt(3, 888);
					ps.setLong(4, 999L);
					ps.setFloat(5, 777f);
					ps.setDouble(6, 999.9999);
					ps.setString(7, "acdefg");
					ps.execute();
				}
			} catch (SQLException e ) {
				e.printStackTrace();
			}
			
//			Retrieve 
			try(Connection con =DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306");PreparedStatement  ps = con.prepareStatement(testSQLs.sql_select_basic);) {
				ps.execute();
				ResultSet rs = ps.getResultSet();
				while(rs.next()) {
					boolean obj = false;
					if(obj) {
						System.out.println(rs.getObject(1).getClass().getSimpleName());
						System.out.println(rs.getObject(2).getClass().getSimpleName());
						System.out.println(rs.getObject(3).getClass().getSimpleName());
						System.out.println(rs.getObject(4).getClass().getSimpleName() );
						System.out.println(rs.getObject(5).getClass().getSimpleName());
						System.out.println(rs.getObject(6).getClass().getSimpleName());
						System.out.println(rs.getObject(7).getClass().getSimpleName());
					}else {
						System.out.println(rs.getBoolean(1));
						System.out.println(rs.getShort(2));
						System.out.println(rs.getInt(3));
						System.out.println(rs.getLong(4));
						System.out.println(rs.getFloat(5));
						System.out.println(rs.getDouble(6));
						System.out.println(rs.getString(7));
					}
				}
			} catch (SQLException  e) {
				e.printStackTrace();
			}
		}
}


