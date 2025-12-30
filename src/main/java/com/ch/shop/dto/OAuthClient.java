package com.ch.shop.dto;

import lombok.Data;

// 비단 구글 뿐만 아니라 네이버 카카오 등 IDP 연동 시 필요한 정보들을 담아놓을 객체
@Data
public class OAuthClient {
	private String provider; 	// google, naver, kakao 구분값
	private String clientId; 		// 개발자 콘솔에서 앱 등록 시 발급받은 클리이언트 ID (공개해도 상관없음)
	private String clientSecret; 	// 발급받은 비밀번호 (비공개)
	private String authorizeUrl; 	// 클라이언트가 sns 로그인을 시도할 때 요청 대상이 되는 URL
	private String tokenUrl; 	// 리소스 오너의 정보를 조회할 때 사용할 요청 주소
	private String userInfoUrl;		// 구글에 등록된 유저의 정보를 조회할 때 사용할 URL
	private String scope;
	private String redirectUri;		// Provider 로 부터 콜백받을 주소
}
