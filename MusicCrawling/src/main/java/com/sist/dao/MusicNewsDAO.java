package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MusicNewsDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public MusicNewsDAO()
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
		public void musicNewsInsert(MusicNewsVO vo)
		{
			try
			{
				getConnection();
				String sql="INSERT INTO MusicNews VALUES("
						+"mn_no_seq.nextval, ?, ?, ?, ?, ?, ?) ";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, vo.getCateno());
				ps.setString(2, vo.getTitle());
				ps.setString(3, vo.getRegdate());
				ps.setString(4, vo.getPoster());
				ps.setString(5, vo.getPoster2());
				ps.setString(6, vo.getContent());
				
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
