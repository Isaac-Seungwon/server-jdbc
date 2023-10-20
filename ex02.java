package com.test.jdbc;

import java.sql.Connection;

public class ex02 {
	public static void main(String[] args) {
		//1. DB 연결
		//2. DB 연결 확인
		//3. DB 연결 종료
		
		Connection conn = null;
		
		try {
			conn = DBUtil.open();
			
			System.out.println(conn.isClosed());
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
