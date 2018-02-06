package com.jianyu.db.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author jianyu.bai
 *
 */
public class JdbcUtil {
	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		Properties prop = new Properties();
		ClassLoader classLoader = JdbcUtil.class.getClassLoader();// 读取属性文件xxxxx.properties
		InputStream in = classLoader.getResourceAsStream("db.properties");
		prop.load(in);

		String driveName = prop.getProperty("oracle.driveName");
		String connStr = prop.getProperty("oracle.driveName");
		String userName = prop.getProperty("oracle.driveName");
		String passWord = prop.getProperty("oracle.driveName");
		String sql = "select * from sims_baseinfo_retail";

		String mysqlDriveName = prop.getProperty("mysql.driveName");
		String mysqlConnStr = prop.getProperty("mysql.driveName");
		String mysqlUserName = prop.getProperty("mysql.driveName");
		String mysqlPassWord = prop.getProperty("mysql.driveName");
		String mysqlSql = "select * from t_user";

		Class.forName(driveName);
		Connection conn = DriverManager.getConnection(connStr, userName, passWord);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery(); // pstmt.executeUpdate()
		while (rs.next()) {
			System.out.println(rs.getString(1) + " " + rs.getString(3));
		}
		rs.close();
		pstmt.close();
		conn.close();
	}
}
