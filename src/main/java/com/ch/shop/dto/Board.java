package com.ch.shop.dto;

import lombok.Data;

@Data
public class Board {
	
	private int board_id;
	private String title;
	private String writer;
	private String content;
	private int hit;
	private String regdate;
	
}
