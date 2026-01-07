package com.ch.shop.model.order;

import java.util.Map;

import com.ch.shop.dto.Cart;

public interface RedisCartDAO {
	public void addItem(Cart cart);		// 장바구니 추가
	public Map<Integer, Integer> getCart(Cart cart); 	// 장바구니 목록
	public void update(Cart cart); 	 // 장바구니 수정
	public void remove(Cart cart);	 // 장바구니 삭제
	public void removeAll(Cart cart);	 // 장바구니 비우기(모두 삭제)
}
