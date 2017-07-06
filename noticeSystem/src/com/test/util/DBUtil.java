package com.test.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


//�������ݿ�Ĺ�����
public class DBUtil {

	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=noticeSystem";
	private static String uName = "sa";
	private static String passwd = "123";

	
	//������ݿ����ӵķ���
	public static Connection getConnection() {
		
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, uName, passwd);
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	//�ر���Դ�ķ���
	public static void closeConn(Connection conn,Statement stm) {
		
		if(stm!=null){
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(getConnection());
	}
	
	
}