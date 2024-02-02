package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.mapper.*;
import com.sist.vo.MemberVO;
@Repository
public class MemberDAO {
	@Autowired
	private MemberMapper mapper;
	
	public MemberVO isLogin(String id, String pwd)
	{
		MemberVO vo=new MemberVO();
		int count=mapper.idCount(id);
		if(count==0) // id 없을때
		{
			vo.setMsg("NOID");
		}
		else // id 있을때
		{
			MemberVO dbvo=mapper.isLogin(id);
			if(pwd.equals(dbvo.getPwd())) // 비밀번호 같을 때
			{
				vo.setMsg("OK");
				vo.setName(dbvo.getName())
				;
				vo.setId(dbvo.getId());
			}
			else // 비밀번호 다를 때
			{
				vo.setMsg("NOPWD");
			}
		}
		return vo;
	}
}
