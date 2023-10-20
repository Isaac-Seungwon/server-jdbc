package com.test.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Ex04_PreparedStatement {
	
	public static void main(String[] args) {

		String name = "John";
		String age = "24";
		String gender = "m";
		String address = "서울시 강남구's 일원동";

		String sql = "";
		int result;
			
		/*
		name = name.replace("'", "''");
		address = address.replace("'", "''");
		*/
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		
		try {
			conn = DBUtil.open();			
			
			//Statement
			/*
			sql = String.format("INSERT INTO tblAddress (seq, name, age, gender, address, regdate) VALUES (seqAddress.nextVal, '%s', %s, '%s', '%s', default)", name, age, gender, address);
			stat = conn.createStatement();
			result = stat.executeUpdate(sql);
			System.out.println(result);
			*/
			
			//PreparedStatement
			sql = "INSERT INTO tblAddress (seq, name, age, gender, address, regdate) VALUES (seqAddress.nextVal, ?, ?, ?, ?, default)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, name);
			pstat.setString(2, age);
			pstat.setString(3, gender);
			pstat.setString(4, address);
			result = pstat.executeUpdate();
			System.out.println(result);

			pstat.close();
			/* stat.close(); */
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
