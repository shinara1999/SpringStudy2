package com.sist.dao;

import lombok.Data;

@Data
public class MusicNewsVO {
	private int no, cateno;
	private String title, regdate, poster, poster2, content;
}
