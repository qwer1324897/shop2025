package com.ch.shop.model.order;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.ch.shop.dto.Cart;
import com.ch.shop.exception.CartException;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class RedisCartDAOImpl implements RedisCartDAO{
	
	// Redis 는 테이블같은 스키마 없이 key - value 값만을 저장. 따라서 장바구니와 관련된 데이터를 넣고자 한다면,
	// 장바구니임을 표시해야 한다. 설계 >> [cart:member_id(누가)  :  product_id(어떤 상품을)  :  ea(몇개나)]
	
	private static final String CART_KEY_PREFIX = "cart:";	// 장바구니의 키로 사용될 접두어. 매번 "cart:" 붙이는 건 짜치니까 미리 전역변수로 선언.

	private String getCartKey(int member_id) {		// 이 메서드를 호출하면 cart:23, cart:34 등의 키값을 만들고 반환해줌
		return CART_KEY_PREFIX+member_id;
	}
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public void addItem(Cart cart) throws CartException{
		// 장바구니에서 넘어온 값이 개수가 0이면 작업 중단
		if(cart.getEa() <= 0) {
			throw new CartException("수량은 1개 이상이어야 합니다.");
		}
		
		HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
		
		String key = getCartKey(cart.getMember_id());	// 장바구니에 사용될 키
		
		try {
			log.debug("Redis 에 들어갈 데이터는 " + key + cart.getProduct_id() + cart.getEa());
			
			Long quantity = hashOperations.increment(key , Integer.toString(cart.getProduct_id()) , (long)cart.getEa());
			
			if(quantity <= 0) {
				throw new CartException("장바구니 수량이 유효하지 않습니다.");
			}
		}	catch(CartException e) {	// 비즈니스 업무적 예외. (ex. 제대로 들어갔다, 안 들어갔다)
			throw e;
		}	catch (Exception e) {
			e.printStackTrace();
			throw new CartException("장바구니 상품 등록 과정 중 오류 발생", e);
		}
	}

	@Override
	public Map<Integer, Integer> getCart(Cart cart) {
		return null;
	}

	@Override
	public void update(Cart cart) {
		
	}

	@Override
	public void remove(Cart cart) {
		
	}

	@Override
	public void removeAll(Cart cart) {
		
	}

}
