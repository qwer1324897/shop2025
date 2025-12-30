package com.ch.shop.test.food;

//  현실의 요리사를 정의
public class Cook {
	
	private Pan pan;	// has a 관계. 요리사가 프라이팬을 가지고 있다.
	
	public Cook(Pan pan) {
		this.pan = pan;
//		pan = new Induction();		// new <ㅡ 이거 자체가 뒤에 정확한 자료형을 따르는 생성자가 오기 때문에, 
												// 아무리 has a 관계를 상위 자료형으로 느슨하게 처리할 지라도 소용이 없다. 따라서 굳이 현재 클래스에서 직접 인스턴스를 생성하는 게 아닌
												// 외부의 주체가 대신 인스턴스를 생성하여, 메서드로 주입을 시켜주면 된다.
												// 스프링에서는 이 외부의 주체가 스프링 애플리케이션 컨텍스트라는 객체가 담당하게 된다.
	}
	
	public void setPan(Pan pan) {
		this.pan = pan;
	}		// 특정 객체를 필요로 할 때는 그 객체의 상위 자료형을 매개변수로 갖는 setter 혹은 생성자를 준비하면 된다.
	
	public void makeFood() {
		pan.boil(); 	  
	}
	
}
