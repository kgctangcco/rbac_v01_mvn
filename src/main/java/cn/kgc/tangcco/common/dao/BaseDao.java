package cn.kgc.tangcco.common.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class BaseDao {
	private static DataSource dataSource = new ComboPooledDataSource("rbac");
	private static QueryRunner queryRunner = new QueryRunner(dataSource);
	public static int update(String sql,Object...objects) {
		try {
			return queryRunner.update(sql, objects);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public static int queryCount(String sql,Object...objects) {
		try {
			return queryRunner.query(sql, new ScalarHandler<Long>(),objects).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public static <T> List<T> queryList(String sql,Class<T> clazz,Object...objects){
		try {
			 return queryRunner.query(sql, new BeanListHandler<>(clazz),objects);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static <T> T queryEntity(String sql,Class<T> clazz,Object...objects){
		try {
			 return queryRunner.query(sql, new BeanHandler<>(clazz),objects);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//事务
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
	public static Connection getConnection() {
		try {
			Connection conn = threadLocal.get();
			if (conn==null) {
				conn = dataSource.getConnection();
				threadLocal.set(conn);
			}
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void closeConnection() {
		Connection conn = threadLocal.get();
		if (conn!=null) {
			try {
				conn.close();
				threadLocal.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
} 
 