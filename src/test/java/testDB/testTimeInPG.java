package testDB;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;

import com.zzy.trace.db.DBUtilsPlus;

public class testTimeInPG {
	public static void main(String[] args) throws SQLException {
		//sql-3不可以
//		String sql_3 = "insert into time (testtime,testdate) values(?,?)";
//		DBUtilsPlus.executeInsert(sql_3, 
//							  new Time(System.currentTimeMillis()),
//							  new java.util.Date(System.currentTimeMillis()));
		//可以
		String sql_1 = "insert into time (testtime,testdate) values(?,?)";
		DBUtilsPlus.executeInsert(sql_1, 
							  new Time(System.currentTimeMillis()),
							  new Date(System.currentTimeMillis()));
		//可以
		String sql_2 = "insert into time (testtime,testdate) values(?,?)";
		DBUtilsPlus.executeInsert(sql_2, 
							  LocalTime.now(),
							  LocalDate.now());
		
		//可以
		String sql_4 = "insert into time (testtime,testdate,testtimestamp) values(?,?,?)";
		DBUtilsPlus.executeInsert(sql_4, 
							  new Time(System.currentTimeMillis()),
							  new Date(System.currentTimeMillis()),
							  LocalDateTime.now());
		//可以
		String sql_5 = "insert into time (testtime,testdate,testtimestamp) values(?,?,?)";
		DBUtilsPlus.executeInsert(sql_5, 
							  new Time(System.currentTimeMillis()),
							  new Date(System.currentTimeMillis()),
							  OffsetDateTime.now());
		//可以
		String sql_6 = "insert into time (testtime,testdate,testtimestamp,testzone) values(?,?,?,?)";
		DBUtilsPlus.executeInsert(sql_6, 
							  new Time(System.currentTimeMillis()),
							  new Date(System.currentTimeMillis()),
							  OffsetDateTime.now(),
							  OffsetDateTime.now());
	}
}
