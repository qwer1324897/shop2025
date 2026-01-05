package com.ch.shop.controller.shop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ch.shop.dto.Cart;
import com.ch.shop.dto.Member;

@Controller
public class CartController {

	// 장바구니 메인 요청 처리
	@GetMapping("/cart/main")
	public String getMain(HttpSession session) {
		
		String viewName = "";
		// 로그인 세션 체크
		Member member = (Member)session.getAttribute("member");
		
		if (member == null) {	// 로그인 하지 않은 경우
			viewName = "shop/member/login";		// 로그인 폼으로 이동시킴.
		} else {	// 로그인 한 경우
			viewName = "shop/cart/list";
		}
		return viewName;
	}
	
	/*
	 장바구니는 저장 기능인데, 3가지 기술로 구현이 가능하다.
	 1. session - 별도의 db가 필요없이 메모리상에서 구현 가능하고, 세션 소멸시 자동으로 삭제된다.
	 				- 분산환경으로 구현된 사이트의 경우 하나의 쇼핑몰을 여러대의 서버가 구동되어 운영되므로, 세션이 공유될 수 없다. 또한 메모리 낭비가 심하다.
	 				- 따라서 소규모, 테스트, 연구분야에서만 사용되고 실 서버 운영 시 사용되진 않는다.
	 				
	 2. 관계형 DB(Mysql) - 원하는 기간만큼 제한없이 저장해놓을 수 있으나, 주문이 완료되면 개발자가 삭제하는 처리를 별도로 해야함.
	 				  			 - 또한 사용자가 많을 경우 DB 용량이 커진다.
	 
	 3. NoSQL DB(Redis) - 가벼운 경량의 메모리 DB. 기존의 RDBMS(관계형 DB) 와 달리, 테이블, 컬럼 등의 스키마가 존재하지 않고 Map 구조로 저장됨.
	 							   - 속도가 매우 빠르다. 단 메모리 용량을 많이 먹는다. 또한 데이터의 유효기간을 명시할 수 있으므로 개발자가 별도의 삭제작업을 하지 않아도 된다.
	 */
	@GetMapping("/cart/add")
	public String addCart(@RequestParam(defaultValue = "0") int product_id, HttpSession session) {
		
		// 클라이언트가 전송한 상품의 product_id, 갯수를 이용하여 Cart 생성하고 보관.
		// 그리고 이 생성된 Cart 인스턴스를 세션에 저장.
		Cart cart = new Cart();
		
		cart.setProduct_id(product_id);
		cart.setEa(product_id);	// 넘겨받을 예정
		cart.setProduct_name(null);	// 상품명을 넘겨 받아야 함
		cart.setFilename(null);	// 얘도 넘겨받아야 함
		cart.setPrice(product_id);	// 얘도 넘겨받아야 함
		
		Map cartMap = new HashMap<Integer, Cart>();
		
		session.setAttribute("cart", cartMap);
		
		return null;
	}
}




