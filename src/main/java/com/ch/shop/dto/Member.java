package com.ch.shop.dto;

import lombok.Data;

@Data
public class Member {
	private int member_id;
	private String home_id;	// sns 회원의 경우 null
	private String home_pass;	// sns 회원의 경우 null
	private String provider_userid;		// homepage 회원의 경우 null
	private String name;	// 프로바이더 측에서는 실명을 주지 않는다. 따라서 닉네임 수준.
	private String email;		// 프로바이더에 따라 이메일을 기본으로 제공하지 않을 수도 있다.(카카오)
	private String regdate;
	private String updated;
	
	private Provider provider;		// 외래키
}
