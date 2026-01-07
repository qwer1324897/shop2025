package com.ch.shop.model.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch.shop.dto.Cart;

@Repository
public class CartServiceImpl implements CartService{

	@Autowired
	private RedisCartDAO redisCartDAO;
	
	@Override
	public void regist(Cart cart) {
		redisCartDAO.addItem(cart);
	}

	@Override
	public List getList(Cart cart) {
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
