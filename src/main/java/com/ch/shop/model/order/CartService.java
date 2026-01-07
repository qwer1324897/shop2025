package com.ch.shop.model.order;

import java.util.List;

import com.ch.shop.dto.Cart;

// 서비스는 장바구니 구현이 redis 이건 session 이건 상관없이 추상적으로 업무를 수행시켜야한다.
public interface CartService {
	public void regist(Cart cart);	   // 장바구니 등록
	public List getList(Cart cart);		// 장바구니 목록
	public void update(Cart cart);	// 장바구니 수정
	public void remove(Cart cart);	// 장바구니 부분 삭제
	public void removeAll(Cart cart); 	// 장바구니 비우기
}
