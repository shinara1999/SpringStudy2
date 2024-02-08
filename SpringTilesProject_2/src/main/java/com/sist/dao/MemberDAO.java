package com.sist.dao;
import com.sist.vo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;

@Repository
public class MemberDAO {
	@Autowired
	private MemberMapper mapper;
	
	public MemberVO isLogin(String id, String pwd)
	{
		MemberVO vo=new MemberVO();
		// ID 존재여부 확인
		int count=mapper.idCount(id);
		if(count==0) // ID 없는상태
		{
			vo.setMsg("NOID");
		}
		else // ID 있는상태
		{
			MemberVO dbVO=mapper.isLogin(id);
			if(pwd.equals(dbVO.getPwd())) // PWD 일치할 때 => 로그인 O
			{
				vo.setMsg("OK");
				// session에 저장되는 대상들
				vo.setId(dbVO.getId());
				vo.setName(dbVO.getName());
			}
			else // PWD 불일치
			{
				vo.setMsg("NOPWD");
			}
		}
		return vo;
	}
}
