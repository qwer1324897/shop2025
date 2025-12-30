package com.ch.shop.dto;

import lombok.Data;

@Data
public class NaverUserResponse {
	private String resultcode;
	private String message;
	private NaverUser response;
}
