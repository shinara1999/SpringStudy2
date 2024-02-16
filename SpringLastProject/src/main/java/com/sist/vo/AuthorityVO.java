package com.sist.vo;

import lombok.Data;

/*
	USERID    NOT NULL VARCHAR2(20)  
	AUTHORITY NOT NULL VARCHAR2(200)
	
	보안 (security) => id (X) => userId, userName, userPwd 사용해야 한다.
		      권한 ==> ROLE_ADMIN, ROLE_MANAGER, ROLE_USER, ROLE_GUEST
		      
	=> 1. 비밀번호 암호화
	=> 2. 권한 부여
	=> 3. 자동 로그인
	=> 4. ID 저장
	=> 메소드 보안
	
 */
@Data
public class AuthorityVO {
	private String userId, authority;
}
