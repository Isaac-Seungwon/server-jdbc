package com.test.jdbc;

import java.sql.*;

import oracle.jdbc.OracleTypes;

public class Ex05_CallableStatement {
	public static void main(String[] args) {

		// m1();
		// m2();
		m3();
		//m4();
		//m5();
	}

	private static void m5() {
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
	
		try {
			conn = DBUtil.open();
	
			String sql = "{ call procM5(?) }";
	
			stat = conn.prepareCall(sql);
			
			stat.registerOutParameter(1, OracleTypes.CURSOR);
			
			stat.executeQuery();
			
			rs = (ResultSet)stat.getObject(1);
			
			while (rs.next()) {
				System.out.println(rs.getString("name"));
				System.out.println(rs.getInt("age"));
				System.out.println(rs.getString("address"));
				System.out.println();
			}
			
			rs.close();
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void m4() {
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.open();

			String sql = "{ call procM4(?, ?, ?) }";
			
			stat = conn.prepareCall(sql);
			
			stat.registerOutParameter(1, OracleTypes.VARCHAR);
			stat.registerOutParameter(2, OracleTypes.NUMBER);
			stat.registerOutParameter(3, OracleTypes.VARCHAR);
			
			stat.executeQuery();
			
			System.out.println(stat.getString(1));
			System.out.println(stat.getInt(2));
			System.out.println(stat.getString(3));
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void m3() {
		Connection conn = null;
		CallableStatement stat = null;

		try {
			conn = DBUtil.open();
			
			String sql = "{ call procM3(?) }";
			
			stat = conn.prepareCall(sql);
			
			//out 매개변수
			stat.registerOutParameter(1, OracleTypes.NUMBER);
			
			//int result = stat.executeUpdate();
			
			stat.executeQuery(); //ResultSet을 받지 않음
			int cnt = stat.getInt(1); //out 매개변수 읽기
			
			System.out.println(cnt);
			
			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void m2() {
		Connection conn = null;
		CallableStatement stat = null;

		try {
			conn = DBUtil.open();

			String sql = "{ call procM2(?, ?, ?, ?) }"; // 호출 방법
			stat = conn.prepareCall(sql);

			stat.setString(1, "Lee");
			stat.setString(2, "24");
			stat.setString(3, "m");
			stat.setString(4, "서울시 강남구 역삼동");

			int result = stat.executeUpdate();
			System.out.println(result);

			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void m1() {
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.open();

			String sql = "{ call procM1 }"; // 호출 방법
			stat = conn.prepareCall(sql);

			int result = stat.executeUpdate();
			System.out.println(result);

			stat.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}