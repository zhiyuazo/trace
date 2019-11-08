package db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.hutool.core.bean.BeanUtil;


/**
 * 数据库jdbc常用操作方法
 * 
 * @author zzy
 *
 */
public class DBUtils {

	private static final Logger logger = LoggerFactory.getLogger(DBUtils.class);

//	private static final String configfile = "jdbc";

	/**
	 * 获取jdbc连接
	 * 
	 * @return
	 */
//	public static Connection getConnection() {
//		Connection conn = null;
//		String driver = PropertiesUtils.getString(configfile, "driverClassName");
//		String url = PropertiesUtils.getString(configfile, "url");
//		String username = PropertiesUtils.getString(configfile, "username");
//		String password = PropertiesUtils.getString(configfile, "password");
//		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, username, password);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			throw new RuntimeException("jdbc connection exception!\n" + e.getMessage());
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException("jdbc connection exception!\n" + e.getMessage());
//		}
//		return conn;
//	}

	/**
	 * 使用默认dbcp连接池，执行sql语句
	 * 
	 * @param sql
	 * @return <code>true</code> if the first result is a <code>ResultSet</code>
	 *         object; <code>false</code> if it is an update count or there are
	 *         no results
	 * @throws SQLException
	 */
	public static boolean executeSql(String sql) throws SQLException {
		return executeSql(DBPool.getConnection(), sql);
	}

	/**
	 * 指定jdbc连接，执行sql语句
	 * 
	 * @param conn
	 *            jdbc连接
	 * @param sql
	 * @return <code>true</code> if the first result is a <code>ResultSet</code>
	 *         object; <code>false</code> if it is an update count or there are
	 *         no results
	 * @throws SQLException
	 */
	public static boolean executeSql(Connection conn, String sql) throws SQLException {
		logger.debug("execute sql : " + sql);
		boolean b = false;
		Statement stat = null;
		try {
			stat = conn.createStatement();
			b = stat.execute(sql);
		} catch (SQLException e) {
			throw e;
		} finally {
			close(conn, stat, null);
		}
		return b;
	}

	/**
	 * 使用默认dbcp连接池，指定jdbc连接，执行insert预处理语句，支持多类型参数
	 * 
	 * @param sql
	 * @param params
	 *            对应预处理语句中"?"号,参数顺序对应语句中"?"号顺序
	 * @throws SQLException
	 */
	public static boolean executeInsert(String sql, Object... params) throws SQLException {
		return executeInsert(DBPool.getConnection(), sql, params);
	}

	/**
	 * 指定jdbc连接，执行insert预处理语句，支持多类型参数
	 * 
	 * @param conn
	 *            jdbc连接
	 * @param sql
	 * @param params
	 *            对应预处理语句中"?"号,参数顺序对应语句中"?"号顺序
	 * @throws SQLException
	 */
	public static boolean executeInsert(Connection conn, String sql, Object... params) throws SQLException {
		logger.debug("execute insert sql : " + sql + " | params : " + paramsToString(params));
		return executePrepareSql(conn, sql, params);
	}

	/**
	 * 使用默认dbcp连接池，批量执行sql
	 * 
	 * @param sqls
	 * @throws SQLException
	 * @return an array of update counts containing one element for each command
	 *         in the batch. The elements of the array are ordered according to
	 *         the order in which commands were added to the batch.
	 */
	public static int[] executeBatch(List<String> sqls) throws SQLException {
		return executeBatch(DBPool.getConnection(), sqls);
	}

	/**
	 * 指定jdbc连接，批量执行sql
	 * 
	 * @param conn
	 *            jdbc连接
	 * @param sqls
	 * @throws SQLException
	 * @return an array of update counts containing one element for each command
	 *         in the batch. The elements of the array are ordered according to
	 *         the order in which commands were added to the batch.
	 */
	public static int[] executeBatch(Connection conn, List<String> sqls) throws SQLException {
		logger.debug("execute batch sql size : " + sqls.size());
		int[] rs = null;
		Statement stat = null;
		try {
			stat = conn.createStatement();
			for (String str : sqls) {
				stat.addBatch(str);
			}
			rs = stat.executeBatch();
		} catch (SQLException e) {
			throw e;
		} finally {
			close(conn, stat, null);
		}
		return rs;
	}

	/**
	 * 默认dbcp连接池，执行Update预处理语句
	 * 
	 * @param sql
	 * @param params
	 *            对应预处理语句中"?"号,参数顺序对应语句中"?"号顺序
	 * @throws SQLException
	 */
	public static boolean executeUpdate(String sql, Object... params) throws SQLException {
		return executeUpdate(DBPool.getConnection(), sql, params);
	}

	/**
	 * 指定jdbc连接，执行Update预处理语句
	 * 
	 * @param conn
	 *            指定jdbc连接
	 * @param sql
	 * @param params
	 *            对应预处理语句中"?"号,参数顺序对应语句中"?"号顺序
	 * @throws SQLException
	 */
	public static boolean executeUpdate(Connection conn, String sql, Object... params) throws SQLException {
		logger.debug("execute update sql : " + sql + " | params : " + paramsToString(params));
		return executePrepareSql(conn, sql, params);
	}


	/**
	 * 默认dbcp连接池，执行Delete预处理语句
	 * 
	 * @param sql
	 * @param params
	 *            对应预处理语句中"?"号,参数顺序对应语句中"?"号顺序
	 * @throws SQLException
	 */
	public static boolean executeDelete(String sql, Object... params) throws SQLException {
		return executeDelete(DBPool.getConnection(), sql, params);
	}

	/**
	 * 指定jdbc连接，执行Delete预处理语句
	 * 
	 * @param conn
	 *            指定jdbc连接
	 * @param sql
	 * @param params
	 *            对应预处理语句中"?"号,参数顺序对应语句中"?"号顺序
	 * @throws SQLException
	 */
	public static boolean executeDelete(Connection conn, String sql, Object... params) throws SQLException {
		logger.debug("execute delete sql : " + sql + " | params : " + paramsToString(params));
		return executePrepareSql(conn, sql, params);
	}

	/**
	 * 执行预处理语句
	 * 
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	protected static boolean executePrepareSql(Connection conn, String sql, Object... params) throws SQLException {
		boolean b = false;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql);

			int i = 0;
			for (Object p : params) {
				i++;
				stat.setObject(i, p);
			}

			b = stat.execute();
		} catch (SQLException e) {
			throw e;
		} finally {
			close(conn, stat, null);
		}

		return b;
	}

	////// ***************** 查询 ************************/////

	/**
	 * 指定jdbc连接，执行sql查询,并回调遍历处理结果
	 * 
	 * @param conn
	 *            jdbc连接
	 * @param sql
	 * @param params
	 * @param callback
	 *            DBUtils.QueryCallback.deal(ResultSet)回调方法
	 * @throws SQLException
	 */
	public static void executeQueryCallback(Connection conn, String sql, Object[] params, QueryCallback callback) throws SQLException {
		logger.debug("execute query sql : " + sql + " | params : " + paramsToString(params));

		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.prepareStatement(sql);

			int i = 0;
			if (params != null) {
				for (Object p : params) {
					if (p != null) {
						stat.setObject(i, p);
						i++;
					}

				}
			}
			rs = stat.executeQuery();

			while (rs.next()) {
				boolean stop = callback.deal(rs);
				if (stop) {
					//break;
				}
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			close(conn, stat, null);
			if (rs!=null) {
				rs.close();
			}
		}
	}
	
	public static void executeQueryCallbacks(Connection conn, String sql, Object[] params, QueryCallback callback) throws SQLException {
		logger.debug("execute query sql : " + sql + " | params : " + paramsToString(params));

		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.prepareStatement(sql);

			int i = 0;
			if (params != null) {
				for (Object p : params) {
					if (p != null) {
						stat.setObject(i, p);
						i++;
					}

				}
			}
			rs = stat.executeQuery();

			while (rs.next()) {
				boolean stop = callback.deal(rs);
				if (stop) {
					//break;
				}
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			/*close(conn, stat, null);*/
			if (rs!=null) {
				rs.close();
			}
		}
	}

	/**
	 * 默认dbcp数据源，执行sql查询,并回调遍历处理结果
	 * 
	 * @param sql
	 * @param callback
	 *            DBUtils.QueryCallback.deal(ResultSet)回调方法
	 * @throws SQLException
	 */
	public static void executeQueryCallback(Connection conn, String sql, QueryCallback callback) throws SQLException {
		executeQueryCallback(DBPool.getConnection(), sql, null, callback);
	}

	/**
	 * 默认dbcp数据源，执行sql查询,并回调遍历处理结果
	 * 
	 * @param sql
	 * @param callback
	 *            DBUtils.QueryCallback.deal(ResultSet)回调方法
	 * @throws SQLException
	 */
	public static void executeQueryCallback(String sql, Object[] params, QueryCallback callback) throws SQLException {
		executeQueryCallback(DBPool.getConnection(), sql, params, callback);
	}

	/**
	 * 榛樿dbcp鏁版嵁婧愶紝鎵цsql鏌ヨ,骞跺洖璋冮亶鍘嗗鐞嗙粨鏋�
	 * 
	 * @param sql
	 * @param callback
	 *            DBUtils.QueryCallback.deal(ResultSet)鍥炶皟鏂规硶
	 * @throws SQLException
	 */
	public static void executeQueryCallback(String sql, QueryCallback callback) throws SQLException {
		executeQueryCallback(DBPool.getConnection(), sql, null, callback);
	}

	/**
	 * 鎵ц鏌ヨ閬嶅巻鍥炶皟鎺ュ彛
	 * 
	 * @author hhm
	 *
	 */
	public interface QueryCallback {
		/**
		 * 鏌ヨ閬嶅巻澶勭悊鍥炶皟鏂规硶
		 * 
		 * @param rs
		 * @return true 鍋滄閬嶅巻 false缁х画閬嶅巻
		 */
		boolean deal(ResultSet rs) throws SQLException;
	}

	/**
	 * 鎸囧畾jdbc杩炴帴锛屾墽琛岄澶勭悊鏌ヨ璇彞锛屾彁渚涘绫诲瀷鍙傛暟
	 * 
	 * @param conn
	 *            jdbc杩炴帴
	 * @param sql
	 * @param params
	 *            瀵瑰簲棰勫鐞嗚鍙ヤ腑"?"鍙�鍙傛暟椤哄簭瀵瑰簲璇彞涓�?"鍙烽『搴�
	 * @return
	 * @throws SQLException
	 */
	public static List<Object[]> executeQuery(Connection conn, String sql, Object... params) throws SQLException {
		final List<Object[]> result = new ArrayList<Object[]>();

		executeQueryCallback(conn, sql, params, new QueryCallback() {

			@Override
			public boolean deal(ResultSet rs) throws SQLException {
				int columnCount = rs.getMetaData().getColumnCount();
				Object[] row = new Object[columnCount];
				for (int index = 0; index < columnCount; index++) {
					row[index] = rs.getObject(index + 1);
				}
				result.add(row);
				return false;
			}

		});
		return result;
	}
	/**
	 * 鎵ц棰勫鐞嗘煡璇㈣鍙ワ紝鎻愪緵澶氱被鍨嬪弬鏁�
	 * @param sql
	 * @param params 瀵瑰簲棰勫鐞嗚鍙ヤ腑"?"鍙�鍙傛暟椤哄簭瀵瑰簲璇彞涓�?"鍙烽『搴�
	 * @return
	 * @throws SQLException
	 */
	public static List<Object[]> executeQuery1(String sql, Object... params) throws SQLException{
		logger.debug("execute query sql : "+sql+" | params : "+paramsToString(params));
		List<Object[]> result = new ArrayList<Object[]>();
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DBPool.getConnection();
			stat = conn.prepareStatement(sql);
			
			int i=0;
			for (Object p : params) {
				i++;
				stat.setObject(i, p);
			}
			rs = stat.executeQuery();
			
			int columnCount = rs.getMetaData().getColumnCount();
			while(rs.next()){
				Object[] row = new Object[columnCount];
				for(int index=0; index<columnCount; index++){
					row[index] = rs.getObject(index+1);
				}
				result.add(row);
			}
			
		} catch (SQLException e) {
			throw e;
		} finally{
			close(conn,stat,null);
		}
		
		return result;
	}
	/**
	 * 指定查询数据库的主机以及用户名和密码的方式进行
	 * @param sql
	 * @param host
	 * @param username
	 * @param password
	 * @param port
	 * @param database
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static List<Object[]> executeQuery1(String sql,String host,String username,String password,String port,String database,Object... params) throws SQLException{
		logger.debug("execute query sql : "+sql+" | params : "+paramsToString(params));
		List<Object[]> result = new ArrayList<Object[]>();
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DBPool.getConnection(host,username,password,port,database);
			stat = conn.prepareStatement(sql);
			
			int i=0;
			for (Object p : params) {
				i++;
				stat.setObject(i, p);
			}
			rs = stat.executeQuery();
			
			int columnCount = rs.getMetaData().getColumnCount();
			while(rs.next()){
				Object[] row = new Object[columnCount];
				for(int index=0; index<columnCount; index++){
					row[index] = rs.getObject(index+1);
				}
				result.add(row);
			}
			
		} catch (SQLException e) {
			throw e;
		} finally{
			close(conn,stat,null);
		}
		
		return result;
	}
	/**
	 * 榛樿dbcp杩炴帴姹狅紝鎵ц棰勫鐞嗘煡璇㈣鍙ワ紝鎻愪緵澶氱被鍨嬪弬鏁�
	 * 
	 * @param conn
	 *            jdbc杩炴帴
	 * @param sql
	 * @param params
	 *            瀵瑰簲棰勫鐞嗚鍙ヤ腑"?"鍙�鍙傛暟椤哄簭瀵瑰簲璇彞涓�?"鍙烽『搴�
	 * @return
	 * @throws SQLException
	 */
	public static List<Object[]> executeQuery(String sql, Object... params) throws SQLException {
		return executeQuery(DBPool.getConnection(), sql, params);
	}

	/**
	 * 鎸囧畾jdbc杩炴帴锛屾墽琛岄澶勭悊鏌ヨ璇彞锛岃繑鍥炴寚瀹氬璞ist缁撴灉闆�
	 * 
	 * @param <T>
	 * @param conn
	 *            jdbc杩炴帴
	 * @param sql
	 * @param params
	 *            瀵瑰簲棰勫鐞嗚鍙ヤ腑"?"鍙�鍙傛暟椤哄簭瀵瑰簲璇彞涓�?"鍙烽『搴�
	 * @param clazz
	 * @return
	 * @throws SQLException
	 */
	public static <T> List<T> executeQuery(Connection conn, String sql, Object[] params, final Class<T> clazz) throws SQLException {
		logger.debug("execute query sql : " + sql + " | params : " + paramsToString(params) + " | class : " + clazz.getName());

		final List<T> result = new ArrayList<T>();

		executeQueryCallback(conn, sql, params, new QueryCallback() {

			@Override
			public boolean deal(ResultSet rs) throws SQLException {
				T t = null;
				try {
					t = clazz.newInstance();
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				ResultSetMetaData meta = rs.getMetaData();
				int columnCount = meta.getColumnCount();
				for (int index = 0; index < columnCount; index++) {

					String label = meta.getColumnLabel(index + 1);
					Object obj = rs.getObject(index + 1);
//					Class<?> type = BeanUtil.getPropertyTypeIgnoreCase(t, label);
					Class<?> type=null;
					if (type == null || obj == null) {
						continue;
					}

					Object val = obj;
					if (obj != null && !type.equals(obj.getClass())) {
						// 澶勭悊oracle涓暟鍊肩被鍨嬭繑鍥濨igDecimal杞崲闂
						if (obj.getClass().equals(BigDecimal.class)) {
							BigDecimal bd = (BigDecimal) obj;
							if (type.equals(Double.class) || type.equals(double.class)) {
								val = bd.doubleValue();
							} else if (type.equals(Long.class) || type.equals(long.class)) {
								val = bd.longValue();
							} else if (type.equals(Integer.class) || type.equals(int.class)) {
								val = bd.intValue();
							}
						}
					}
//					BeanUtils.setPropertyIgnoreCase(t, label, val);
				}
				result.add(t);
				return false;
			}

		});

		return result;
	}

	/**
	 * 榛樿dbcp杩炴帴姹狅紝鎵ц棰勫鐞嗘煡璇㈣鍙ワ紝杩斿洖鎸囧畾瀵硅薄list缁撴灉闆�
	 * 
	 * @param <T>
	 * @param sql
	 * @param params
	 *            瀵瑰簲棰勫鐞嗚鍙ヤ腑"?"鍙�鍙傛暟椤哄簭瀵瑰簲璇彞涓�?"鍙烽『搴�
	 * @param clazz
	 * @return
	 * @throws SQLException
	 */
	public static <T> List<T> executeQuery(String sql, Object[] params, Class<T> clazz) throws SQLException {
		return executeQuery(DBPool.getConnection(), sql, params, clazz);
	}

	/**
	 * 鎸囧畾jdbc杩炴帴锛屾墽琛岄澶勭悊鏌ヨ璇彞锛岃繑鍥炴寚瀹氬璞ist缁撴灉闆�
	 * 
	 * @param <T>
	 * @param conn
	 *            jdbc杩炴帴
	 * @param sql
	 * @param clazz
	 * @return
	 * @throws SQLException
	 */
	public static <T> List<T> executeQuery(Connection conn, String sql, Class<T> clazz) throws SQLException {
		return executeQuery(conn, sql, new Object[0], clazz);
	}

	/**
	 * 榛樿dbcp杩炴帴姹狅紝鎵ц棰勫鐞嗘煡璇㈣鍙ワ紝杩斿洖鎸囧畾瀵硅薄list缁撴灉闆�
	 * 
	 * @param <T>
	 * @param sql
	 * @param clazz
	 * @return
	 * @throws SQLException
	 */
	public static <T> List<T> executeQuery(String sql, Class<T> clazz) throws SQLException {
		return executeQuery(sql, new Object[0], clazz);
	}

	/**
	 * 鎸囧畾jdbc杩炴帴锛屾墽琛岄澶勭悊鏌ヨ璇彞锛岃繑鍥炴寚瀹氬璞ist缁撴灉闆�
	 * 
	 * @param <T>
	 * @param conn
	 *            jdbc杩炴帴
	 * @param sql
	 * @param params
	 *            瀵瑰簲棰勫鐞嗚鍙ヤ腑"?"鍙�鍙傛暟椤哄簭瀵瑰簲璇彞涓�?"鍙烽『搴�
	 * @param clazz
	 * @return
	 * @throws SQLException
	 */
	public static <T> T executeQueryOne(Connection conn, String sql, Object[] params, final Class<T> clazz) throws SQLException {

		final List<T> ls = new ArrayList<T>();

		executeQueryCallback(conn, sql, params, new QueryCallback() {

			@Override
			public boolean deal(ResultSet rs) throws SQLException {
				T t;
				try {
					t = clazz.newInstance();
				} catch (Exception e) {
					e.printStackTrace();
					return true;
				}
				ResultSetMetaData meta = rs.getMetaData();
				int columnCount = meta.getColumnCount();
				for (int index = 0; index < columnCount; index++) {

					String label = meta.getColumnLabel(index + 1);
					Object obj = rs.getObject(index + 1);
//					Class<?> type = BeanUtils.getPropertyTypeIgnoreCase(t, label);
					Class<?> type =null;
					if (type == null || obj == null) {
						continue;
					}

					Object val = obj;
					if (obj != null && !type.equals(obj.getClass())) {
						// 澶勭悊oracle涓暟鍊肩被鍨嬭繑鍥濨igDecimal杞崲闂
						if (obj.getClass().equals(BigDecimal.class)) {
							BigDecimal bd = (BigDecimal) obj;
							if (type.equals(Double.class) || type.equals(double.class)) {
								val = bd.doubleValue();
							} else if (type.equals(Long.class) || type.equals(long.class)) {
								val = bd.longValue();
							} else if (type.equals(Integer.class) || type.equals(int.class)) {
								val = bd.intValue();
							}
						}
					}
//					BeanUtils.setPropertyIgnoreCase(t, label, val);
				}
				ls.add(t);
				return true;
			}

		});
		if (ls.size() > 0) {
			return ls.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 浣跨敤榛樿dbcp杩炴帴姹狅紝鎵ц棰勫鐞嗘煡璇㈣鍙ワ紝杩斿洖涓�璁板綍
	 * 
	 * @param sql
	 * @param params
	 * @param clazz
	 * @return
	 * @throws SQLException
	 */
	public static <T> T executeQueryOne(String sql, Object[] params, Class<T> clazz) throws SQLException {
		return executeQueryOne(DBPool.getConnection(), sql, params, clazz);
	}

	/**
	 * 鎸囧畾jdbc杩炴帴锛屾墽琛岄澶勭悊鏌ヨ璇彞锛岃繑鍥炰竴琛岃褰�
	 * 
	 * @param conn
	 *            jdbc杩炴帴
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static Object[] executeQueryOne(Connection conn, String sql, Object... params) throws SQLException {
		final List<Object[]> ls = new ArrayList<Object[]>();

		executeQueryCallback(conn, sql, params, new QueryCallback() {

			@Override
			public boolean deal(ResultSet rs) throws SQLException {
				ResultSetMetaData meta = rs.getMetaData();
				int columnCount = meta.getColumnCount();
				Object[] row = new Object[columnCount];
				for (int index = 0; index < columnCount; index++) {
					row[index] = rs.getObject(index + 1);
				}

				ls.add(row);
				return true;
			}

		});
		if (ls.size() > 0) {
			return ls.get(0);
		} else {
			return null;
		}
	}
	/**
	 * 鎸囧畾jdbc杩炴帴锛屾墽琛岄澶勭悊鏌ヨ璇彞锛岃繑鍥炰竴琛岃褰�杩炴帴涓嶅叧闂紝鐢ㄤ簬闆嗗悎
	 * 
	 * @param conn
	 *            jdbc杩炴帴
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static Object[] executeQueryOnes(Connection conn, String sql, Object... params) throws SQLException {
		final List<Object[]> ls = new ArrayList<Object[]>();

		executeQueryCallbacks(conn, sql, params, new QueryCallback() {

			@Override
			public boolean deal(ResultSet rs) throws SQLException {
				ResultSetMetaData meta = rs.getMetaData();
				int columnCount = meta.getColumnCount();
				Object[] row = new Object[columnCount];
				for (int index = 0; index < columnCount; index++) {
					row[index] = rs.getObject(index + 1);
				}

				ls.add(row);
				return true;
			}

		});
		if (ls.size() > 0) {
			return ls.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 浣跨敤榛樿dbcp杩炴帴姹狅紝鎵ц棰勫鐞嗘煡璇㈣鍙ワ紝杩斿洖涓�璁板綍
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static Object[] executeQueryOne(String sql, Object... params) throws SQLException {
		return executeQueryOne(DBPool.getConnection(), sql, params);
	}

	/**
	 * 鎸囧畾jdbc杩炴帴锛屾墽琛宑ount鏌ヨ锛岃繑鍥瀋ount鏁�
	 * 
	 * @param conn
	 *            jdbc杩炴帴
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static long executeQueryCount(Connection conn, String sql, Object... params) throws SQLException {
		Object[] obj = executeQueryOne(conn, sql, params);
		if (obj == null) {
			return 0;
		} else {
			return Long.parseLong(String.valueOf(obj[0]));
		}
	}
	
	/**
	 * 鎸囧畾jdbc杩炴帴锛屾墽琛宑ount鏌ヨ锛岃繑鍥瀋ount鏁�杩炴帴涓嶅叧闂紝鐢ㄤ簬闆嗗悎
	 * 
	 * @param conn
	 *            jdbc杩炴帴
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static long executeQueryCounts(Connection conn, String sql, Object... params) throws SQLException {
		Object[] obj = executeQueryOnes(conn, sql, params);
		if (obj == null) {
			return 0;
		} else {
			return Long.parseLong(String.valueOf(obj[0]));
		}
	}

	/**
	 * 浣跨敤榛樿dbcp杩炴帴姹狅紝鎵цcount鏌ヨ锛岃繑鍥瀋ount鏁�
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static long executeQueryCount(String sql, Object... params) throws SQLException {
		return executeQueryCount(DBPool.getConnection(), sql, params);
	}

	/**
	 * 閲婃斁杩炴帴璧勬簮锛孋onnection锛孲tatement锛孯esultSet
	 * 
	 * @param conn
	 * @param stat
	 * @param rs
	 * @throws SQLException
	 */
	public static void close(Connection conn, Statement stat, ResultSet rs) throws SQLException {

		if (rs != null) {
			rs.close();
		}
		if (stat != null) {
			stat.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * 鎵撳嵃鏁扮粍鍙傛暟
	 * 
	 * @param params
	 * @return
	 */
	protected static String paramsToString(Object[] params) {
		String str = "";
		if (params == null || params.length == 0) {
			return "null";
		}
		for (Object p : params) {
			str += String.valueOf(p) + " & ";
		}
		return str;
	}

	/**
	 * 鎺у埗鍙版墦鍗版煡璇㈢粨鏋�
	 * 
	 * @param result
	 */
	public static void printQueryResult(List<Object[]> result) {
		int rownum = 0;
		for (Object[] objects : result) {
			rownum++;

			System.out.print("num : " + rownum + "  =>\t");
			for (Object object : objects) {
				System.out.print(object + "\t");
			}
			System.out.println();
		}
	}
}
