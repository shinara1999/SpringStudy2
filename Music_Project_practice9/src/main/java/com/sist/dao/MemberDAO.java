package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.sist.mapper.MemberMapper;
import com.sist.vo.MemberVO;
import com.sist.vo.MusicBoardVO;

@Repository
public class MemberDAO {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private MemberMapper mapper;
	
	public int memberIdCount(String userId)
	{
		return mapper.memberIdCount(userId);
	}
	public void memberInsert(MemberVO vo)
	{
		mapper.memberInsert(vo);
	}
	public void memberAuthorityInsert(String userId)
	{
		mapper.memberAuthorityInsert(userId);
	}
	public MemberVO memberLogin(String userId, String userPwd)
	{
		MemberVO dbVO=new MemberVO();
		   int count=mapper.memberIdCount(userId);
		   if(count==0)
		   {
			   dbVO.setMsg("NOID");
		   }
		   else
		   {
			   dbVO=mapper.memberLogin(userId);
			   if(encoder.matches(userPwd, dbVO.getUserPwd()))
			   {
				   dbVO.setMsg("OK");
			   }
			   else
			   {
				   dbVO.setMsg("NOPWD");
			   }
		   }
		   return dbVO;
	}
	
	public void lastLoginUpdate(String userId)
	{
		mapper.lastLoginUpdate(userId);
	}
	
	public MemberVO memberSessionData(String userId)
	{
		return mapper.memberSessionData(userId);
	}
	
	// 아이디 찾기
	public String idFindOpen(String userName, String email)
	{
		String msg="";
		int count=mapper.idFind(userName, email);
		if(count==0)
		{
			msg="NO";
		}
		else
		{
			msg=mapper.idFindOpen(userName, email);
		}
		return msg;
	}
	
	// 비밀번호 변경
	public MemberVO pwdChange(String userId, String email, String userPwd)
	{
		return mapper.pwdChange(userId, email, userPwd);
	}
	// 마이페이지 정보 수정
	public String userInfoUpdate(MemberVO vo)
	{
		String result="yes";
		mapper.userInfoUpdate(vo);
		return result;
	}
	
	// 회원 탈퇴
	public String userInfoDelete(String userId)
	{
		return mapper.userInfoDelete(userId);
	}
	
	// 관리자 유저리스트
	public List<MemberVO> userListData()
	{
		return mapper.userListData();
	}
	
}
