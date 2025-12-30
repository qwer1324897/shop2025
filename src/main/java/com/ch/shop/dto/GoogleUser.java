package com.ch.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

// token 을 이용하여 사용자 정보를 요청할 때 서버측에서 보내온 정보를 담게 될 객체
@Data
public class GoogleUser {
	@JsonProperty("sub")
	private String id;	// 구글에서 사용자를 구분하기 위한 open id 를 우리에게 보내줄 때는 id 라는 용어를 사용하지 않고, sub 라는 key 값으로 보내온다.
								// 만약, 지금처럼 개발자가 변수명으로 id 를 사용하고 싶다면, 애노테이션을 명시하여 자동으로 매핑할 수 있다. 
	private String email;
	private Boolean verified_email;
	private String name;
	private String given_name;
	private String family_name;
	private String picture;	// 프로필 사진 URL
	private String locale;	// 언어설정
}