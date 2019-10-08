package testModel;

public class testSQLs {
	public static String sql_insert_basic = "insert into test(testboolean,testshort,testint,testlong,testfloat,testdouble,teststring) values(?,?,?,?,?,?,?)";
	public static String sql_insert_charbyte = "insert into test(testchar,testchararr1,testchararr2,testbyte,testbytearr1,testbytearr2) values(?,?,?,?,?,?)";
	public static String sql_insert_time = "insert into test(testsqltime,testlocaltime,testlocaldate,testsqldate,testlocaldatetime,testoffsetdatetime) values(?,?,?,?,?,?)";
	public static String sql_insert_jsonobj = "insert into test(testjson,testobject) values(?,?)";
	
	public static String sql_select_basic = "select testboolean,testshort,testint,testlong,testfloat,testdouble,teststring from test";
	public static String sql_select_charbyte = "select testchar,testchararr1,testchararr2,testbyte,testbytearr1,testbytearr2 from test";
	public static String sql_select_time = "select testsqltime,testlocaltime,testlocaldate,testsqldate,testlocaldatetime,testoffsetdatetime from test";
	public static String sql_select_jsonobj = "select testjson,testobject from test";
	
//				Object o = c.newInstance();
//				for(Field f : fs ) {
//					String attr = f.getName();
//					if(rs.getObject(attr) != null) {
//						f.set(o, rs.getObject(attr));
//					}
//				}
}
