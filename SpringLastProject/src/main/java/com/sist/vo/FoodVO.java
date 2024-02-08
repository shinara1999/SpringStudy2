package com.sist.vo;

import lombok.Data;

@Data
public class FoodVO {
	private int fno;
	private String poster, name, type, address, phone, theme, price, time, seat, sessionId;
	private double score;
	
}
