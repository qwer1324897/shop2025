package com.ch.shop.test.food;

// 요리를 가스가 아닌 전기로
public class Induction implements Pan{
	
	public void boil() {
		System.out.println("음식을 전기로 데워요");
	}
}
