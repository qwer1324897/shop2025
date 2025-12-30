package com.ch.shop.exception;

// 자바의 RuntimeException 을 상속받아 개발자만의 예외 객체로 커스텀하기 위함
public class ProductException extends RuntimeException{
	
	// 자바에서 부모의 생성자는 물려받지 못 한다. 왜 why 생성자는 해당 객체만의 초기화 작업에 사용되므로, 부모의 생성자마저도 물려받게 되면 내가 부모가 되어버리는 개념
	public ProductException(String msg) {
		super(msg);		// 에러 메세지만을 담는 부모의 생성자 호출
	}
	
	// Throwable 은 예외 객체의 최상위 인터페이스로, 어떤 정류의 에러가 나더라도 이 객체로 받을 수 있다.
	public ProductException(String msg, Throwable e) {
		super(msg, e);		// 에러 메세지와 원인 둘 다 담는 부모의 생성자 호출
	}
	
	public ProductException(Throwable e) {
		super(e);		// 에러 원인만을 담는 부모의 생성자 호출
	}	
	
}
