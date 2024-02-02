package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

public interface BoardMapper {
	// 게시판 목록 출력
	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, num "
			+"FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+"FROM (SELECT /*+ INDEX_DESC(vueBoard vb_no_pk)*/no, subject, name, regdate, hit "
			+"FROM vueBoard)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(@Param("start") int start, @Param("end") int end);
	
	// 게시판 목록 페이징
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM vueBoard")
	public int boardTotalPage();
	
	// 게시판 글 작성
	@Insert("INSERT INTO vueBoard(no, name, subject, content, pwd) "
			+"VALUES(vb_no_seq.nextval, #{name}, #{subject}, #{content}, #{pwd})")
	public void boardInsert(BoardVO vo);
	
	// 게시판 상세보기
	@Update("UPDATE vueBoard SET "
			+"hit=hit+1 "
			+"WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no, name, subject, content, hit, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday "
			+"FROM vueBoard "
			+"WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	
	// 게시판 글 수정
	@Select("SELECT pwd FROM vueBoard "
			+"WHERE no=#{no}")
	public String boardGetPassword(int no);
	
	@Update("UPDATE vueBoard SET "
			+"name=#{name}, subject=#{subject}, content=#{content} "
			+"WHERE no=#{no}")
	public void boardUpdate(BoardVO vo);
	
	// 게시판 글 삭제
	@Delete("DELETE FROM vueBoard "
			+"WHERE no=#{no}")
	public void boardDelete(int no);
}










