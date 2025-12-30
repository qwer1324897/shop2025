package com.ch.shop.test.food;

// DI 에 의해 객체간 정확하고 구체적인 자료형을 보유할 수록 유지보수성이 떨어진다.
// 의존성이 너무 강해서 하나만 바꾸면 나머지 다 바꿔야 하기 때문.
// 따라서 특정 객체가 보유할 객제의 자료형은 그보다 높은 상위 자료형을 정의하여 보유하는 것으로 해결

// 즉 의존성(Dependency)를 낮춘다.
public interface Pan {
	public void boil();
}
