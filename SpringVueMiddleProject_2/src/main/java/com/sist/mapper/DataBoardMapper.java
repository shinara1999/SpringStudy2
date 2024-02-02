package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.DataBoardVO;

public interface DataBoardMapper {
	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, num "
			+"FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+"FROM (SELECT /*+ INDEX_DESC(vueDataboard vdb_no_pk)*/no, subject, name, regdate, hit "
			+"FROM vueDataBoard)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<DataBoardVO> databoardListData(@Param("start") int start, @Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM vueDataBoard")
	public int databoardTotalPage();
	
	@Insert("INSERT INTO vueDataBoard VALUES("
			+"vdb_no_seq.nextval, #{name}, #{subject}, #{content}, #{pwd},"
			+"SYSDATE, 0, #{filename}, #{filesize}, #{filecount})")
	public void databoardInsert(DataBoardVO vo);
	
	/*
			SELECT
				= selectList
				= selectOne
			INSERT, UPDATE, DELETE => void로 설정
	 */
	// 상세보기
	@Update("UPDATE vueDataBoard SET "
			+"hit=hit+1 "
			+"WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no, name, subject, content, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday,"
			+"hit, filename, filesize, filecount "
			+"FROM vueDataBoard "
			+"WHERE no=#{no}")
	public DataBoardVO databoardDetailData(int no);
}
