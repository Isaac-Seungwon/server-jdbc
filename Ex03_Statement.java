package com.test.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Ex03_Statement {

	public static void main(String[] args) {
		//m1();
		//m2();	
		//m3();
		//m4();
		//m5();
		//m6();
		//m7();
		//m8();
		m9();
	}
	
	private static void m9() {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select * from tblAddress";
			
			rs = stat.executeQuery(sql);
			
			System.out.println("[번호]\t[이름]\t[나이]\t[주소]");
			
			while(rs.next()) {
				System.out.printf("%s\t%s\t%s\t%s", rs.getString("seq"), rs.getString("name"), rs.getString("age"), rs.getString("address"));
			}
			
			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void m8() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "SELECT name FROM tblAddress";
			
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}
			
			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void m7() {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("번호: ");
		String seq = scan.nextLine();

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "SELECT * FROM tblAddress WHERE seq = " + seq;
			
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				System.out.println("번호: " + rs.getString("seq"));
				System.out.println("이름: " + rs.getString("name"));
				System.out.println("나이: " + rs.getString("age"));
				System.out.println("성별: " + rs.getString("gender"));
				System.out.println("주소: " + rs.getString("address"));
				System.out.println("날짜: " + rs.getString("regdate"));
			} else {
				System.out.printf("입력한 %s번의 데이터가 없습니다.\n", seq);
			}

			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void m6() {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "SELECT count(*) as cnt FROM tblAddress";
			
			rs = stat.executeQuery(sql);
			
			//System.out.println(rs); //oracle.jdbc.driver.OracleResultSetImpl@41c2284a
			
			rs.next(); //커서 1줄 전진
			
			int count = rs.getInt(1); //index
			
			System.out.println(count);

			count = rs.getInt("cnt"); //컬럼명
			
			System.out.println(count);
			
			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	private static void m() {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtill.open();
			stat = conn.createStatement();
			
			
			
			rs.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	*/

	private static void m5() {
		Scanner scan = new Scanner(System.in);
		
		String name = "";
		String age = "";
		String gender = "";
		String address = "";
		
		Connection conn = null;
		Statement stat = null;
		
		try {
			System.out.print("이름: ");
			name = scan.nextLine();

			System.out.print("나이: ");
			age = scan.nextLine();

			System.out.print("성별(m, f): ");
			gender = scan.nextLine();

			System.out.print("주소: ");
			address = scan.nextLine();
			
			conn = DBUtil.open();
			
			if (!conn.isClosed()) {
				System.out.println("DB 접속 성공");
				
				String sql = String.format("INSERT INTO tblAddress (seq, name, age, gender, address, regdate) VALUES (seqAddress.nextVal, '%s', %s, '%s', '%s', default)", name, age, gender, address);
				
				stat = conn.createStatement();
				
				int result = stat.executeUpdate(sql);
				
				if (result == 1) {
					System.out.println("삽입 성공");
				} else {
					System.out.println("삽입 실패");
				}
				
				stat.close();
				conn.close();
			} else {
				System.out.println("DB 접속 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void m4() {
		Connection conn = null;
		Statement stat = null;
		
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();

			String sql = "CREATE TABLE tblAddress (\r\n"
					+ "	seq NUMBER PRIMARY KEY,\r\n"
					+ "	name VARCHAR2(30) NOT NULL,\r\n"
					+ "	age NUMBER NOT NULL,\r\n"
					+ "	gender CHAR(1) NOT NULL,\r\n"
					+ "	address VARCHAR2(300) NOT NULL,\r\n"
					+ "	regdate DATE DEFAULT sysdate NOT null\r\n"
					+ ")";
			
			int result = stat.executeUpdate(sql);
			
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void m3() {
		Connection conn = null;
		Statement stat = null;
		
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();

			String sql = "DELETE FROM tblAddress WHERE seq = 1";
			
			int result = stat.executeUpdate(sql);
			
			if (result > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}
			
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void m2() {
		Connection conn = null;
		Statement stat = null;
		
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();

			String sql = "UPDATE tblAddress SET age = age + 1";
			
			int result = stat.executeUpdate(sql);
			
			if (result > 0) {
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패");
			}
			
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//반환값이 없는 insert 실행
	private static void m1() {
		Connection conn = null;
		Statement stat = null;
		
		try {
			conn = DBUtil.open(); //DB 접속
			
			if (!conn.isClosed()) {
				System.out.println("DB 접속 성공");
				
				//query
				String sql = "INSERT INTO tblAddress (seq, name, age, gender, address, regdate) VALUES (seqAddress.nextVal, 'Sopia', 21, 'f', '서울시 강남구 대치동', default)";
				
				//stat
				stat = conn.createStatement();
				
				//반환값이 없는 쿼리
				int result = stat.executeUpdate(sql);
				
				if (result == 1) {
					System.out.println("삽입 성공");
				} else {
					System.out.println("삽입 실패");
				}
				
				//자원 해제(정리)
				stat.close();
				conn.close();
			} else {
				System.out.println("DB 접속 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
