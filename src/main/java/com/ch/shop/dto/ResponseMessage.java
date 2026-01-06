package com.ch.shop.dto;

import lombok.Data;

// 클라이언트가 비동기 방식으로 요청을 시도할 때, 그 응답정보가 메세지라면
// 개발자가 매번 요청을 시도할 때마다 문자열 처리를 하는 게 아니라, Jackson 과 같은 자동 컨버터에게 변환을 맡기는 게 편리하다.
// 컨버터를 이용하기 위해서는 응답정보를 표현한 자바의 클래스가 필요하기 때문에, 이 클래스를 정의하고 활용한다.

@Data
public class ResponseMessage {
	private String msg;
}
