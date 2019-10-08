package testJSON;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alibaba.fastjson.JSONObject;

import cn.hutool.json.JSONUtil;
import testModel.testSQLs;
		  
class abc {
	String name = "abc";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
public class testJSONOBJECTtoPG {
		public static void main(String[] args) throws SQLException {
			//INSERT
			try(Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306");PreparedStatement  ps = con.prepareStatement(testSQLs.sql_insert_jsonobj);) {
				boolean obj = true;
				if(obj) {
					byte[] bs = {21,22,22,22};
					JSONObject jo = new JSONObject();
					jo.put("name", "zhiyuan");
					ps.setObject(1, jo.toString());
					ps.setObject(2, JSONUtil.parseObj(new abc()).toJSONString(1).getBytes());
					ps.execute();
				}
			} catch (SQLException e ) {
				e.printStackTrace();
			}
			
//			Retrieve 
			try(Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306");PreparedStatement  ps = con.prepareStatement(testSQLs.sql_select_jsonobj);) {
				ps.execute();
				ResultSet rs = ps.getResultSet();
				while(rs.next()) {
					boolean obj = true;
					if(obj) {
						System.out.println(rs.getObject(1).getClass().getSimpleName());
						System.out.println(rs.getObject(2).getClass().getSimpleName());
						System.out.println(new String( (byte[]) rs.getObject(2) ));
					}
				}
			} catch (SQLException  e) {
				e.printStackTrace();
			}
		}
}


