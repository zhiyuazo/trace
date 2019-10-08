package testDB;

import java.sql.SQLException;

import testModel.father;

public class testGenSql {

	public static void main(String[] args) throws SQLException {
//		String sql1 = DBUtilsPlus.getTableSql(DZZCMessage.class , "syq_bw_dzzc");
//		DBUtilsPlus.getTableSql(DMMBZCMessage.class , "syq_bw_dmzc");
//		DBUtilsPlus.getTableSql(KZMBZCMessage.class , "syq_bw_airzc");
//		DBUtilsPlus.getTableSql(TXZCMessage.class , "syq_bw_txzc");
		String sql1 = db.DBUtilsPlus.getTableSql(father.class , "syq_father");
		
		System.out.println(sql1);
	}
}
