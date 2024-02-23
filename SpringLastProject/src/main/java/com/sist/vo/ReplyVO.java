package com.sist.vo;
/*
 *  NO       NOT NULL NUMBER       
	RNO               NUMBER       
	USERID            VARCHAR2(20) 
	USERNAME NOT NULL VARCHAR2(51) 
	MSG      NOT NULL CLOB         
	REGDATE           DATE 
 */
import java.util.*;

import lombok.Data;
@Data
public class ReplyVO {
  private int no,rno;
  private String userId,userName,msg,dbday;
  private Date regdate;
}