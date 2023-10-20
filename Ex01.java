package com.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Ex01 {
	
	public static void main(String[] args) {
		Connection conn = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "hr";
		String pw = "java1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, id, pw);
			
			System.out.println(conn.isClosed());
			
			System.out.println("질의(SQL) 실행");
			
			conn.close();
			System.out.println(conn.isClosed());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
