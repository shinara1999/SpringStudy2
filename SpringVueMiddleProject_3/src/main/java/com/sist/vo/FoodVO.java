package com.sist.vo;
/*
	 FNO                                       NOT NULL NUMBER
	 POSTER                                    NOT NULL VARCHAR2(1000)
	 NAME                                      NOT NULL VARCHAR2(300)
	 TYPE                                      NOT NULL VARCHAR2(100)
	 ADDRESS                                   NOT NULL VARCHAR2(500)
	 PHONE                                     NOT NULL VARCHAR2(100)
	 SCORE                                              NUMBER(2,1)
	 THEME                                     NOT NULL VARCHAR2(4000)
	 PRICE                                              VARCHAR2(100)
	 TIME                                               VARCHAR2(200)
	 SEAT                                               VARCHAR2(100)
	 CONTENT                                            CLOB
	 LINK                                               VARCHAR2(300)
 */
// 사용자 정의 데이터형 => 메모리할당을 요청하지 않는다.
import lombok.Data;

@Data
public class FoodVO {
	private int fno;
	private String poster, name, type, address, phone, theme, price, time, seat, sessionId;
	private double score;
}
