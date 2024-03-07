package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MapoNatureDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public MapoNatureDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {ex.printStackTrace();}
	}
	// 오라클 연동
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL, "hr", "happy");
		}catch(Exception ex) {ex.printStackTrace();}
	}
	// 오라클 닫기
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	
	// 테이블에 넣기
		public void MapoNatureInsert(MapoNatureVO vo)
		{
			try
			{
				getConnection();
				String sql="INSERT INTO maponature VALUES("
						+"mnn_nno_seq.nextval, ?, ?, ?, ?, ?, ?, ?) ";
				ps=conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getAddress());
				ps.setString(3, vo.getPhone());
				ps.setString(4, vo.getHomepage());
				ps.setString(5, vo.getTag());
				ps.setString(6, vo.getPoster());
				ps.setString(7, vo.getContent());
				
				ps.executeUpdate();
				
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				disConnection();
			}
		}
}
