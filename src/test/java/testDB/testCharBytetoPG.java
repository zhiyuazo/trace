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
//		则  char:'A'    	 --->char    ---> String
//		  chars:"ABC" 	 --->char(8) ---> String
//		  chars:"abcdef" --->varchar ---> String
//		  String:"abcdefg" ---> text ---> String
//		  Byte:"127" ---> int2 --->  Integer
//		  Byte[]:"1,2,3,4" --->bytea ---> Byte[]
//		  result:
//				char ---> String(in PG)
//				byte ---> int (in PG)
//				byte[] ---> bytea
//4. setObject 进入数据,库   getObject 取到数据:
public class testCharBytetoPG {
		public static void main(String[] args) throws SQLException {
			//INSERT
			try(Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306");PreparedStatement  ps = con.prepareStatement(testSQLs.sql_insert_charbyte)) {
				boolean obj = true;
				if(obj) {
					char[] cs = {'a','b','c','d'};
					byte[] bs = {100,101,102,103};
					ps.setObject(1, 'A');
					ps.setObject(2, "AB");
					ps.setObject(3, "ABC");
					ps.setObject(4, 25);
					ps.setObject(5, bs);
					ps.setObject(6, bs);
					ps.execute();
				}else {
					byte[] bs = {100,101,102,103};
					ps.setString(1, "A");
					ps.setString(2, "av");
					ps.setString(3, "av");
					ps.setByte(4, (byte) 127);
					ps.setBytes(5, bs);
					ps.setString(6,new String(bs));
					ps.execute();
				}
			} catch (SQLException e ) {
				e.printStackTrace();
			}
			
//			Retrieve 
			try(Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306");PreparedStatement  ps = con.prepareStatement(testSQLs.sql_select_charbyte);) {
				ps.execute();
				ResultSet rs = ps.getResultSet();
				while(rs.next()) {
					boolean obj = true;
					if(obj) {
						System.out.println(rs.getObject(1).getClass().getSimpleName());
						System.out.println(rs.getObject(2).getClass().getSimpleName());
						System.out.println(rs.getObject(3).getClass().getSimpleName());
						System.out.println(rs.getObject(4).getClass().getSimpleName() );
						System.out.println(rs.getObject(5).getClass().getSimpleName());
						System.out.println(rs.getObject(6).getClass().getSimpleName());
					}else {
						System.out.println(rs.getString(1));
						System.out.println(rs.getString(2));
						System.out.println(rs.getString(3));
						System.out.println(rs.getByte(4));
						System.out.println(rs.getBytes(5));
						System.out.println(rs.getString(6));
					}
				}
			} catch (SQLException  e) {
				e.printStackTrace();
			}
		}
}


