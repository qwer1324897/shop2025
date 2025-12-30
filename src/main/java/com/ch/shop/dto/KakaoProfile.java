package com.ch.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

//네이버와 카카오의 경우, 구글처럼 json을 한 껍데기로 주는 게 아니라 보안 등의 이유로 여러 층 구조로 json을 감싼 형태로 제공해준다.
/*
{
"id": 123456789,
"kakao_account": {
 "email": "user@kakao.com",
 "profile": {
   "nickname": "홍길동",
   "profile_image_url": "http://..."
 }
}
}

카카오의 경우, 위처럼 3겹의 형태로 주기 때문에(구글은 1겹, 네이버는 2겹) dto도 3개를 만든다.
*/ 

// 최하단 알맹이 상자
@Data
public class KakaoProfile {
	private String nickname;
	
	@JsonProperty("profile_image_url")
	private String profileImageUrl;
}
