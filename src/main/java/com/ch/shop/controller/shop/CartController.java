package com.ch.shop.controller.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch.shop.dto.Cart;
import com.ch.shop.dto.Member;
import com.ch.shop.dto.ResponseMessage;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CartController {

	// 장바구니 목록 요청 처리
	@GetMapping("/cart/main")
	public String getMain(HttpSession session, Model model) {
		
//		String viewName = "";
//		// 로그인 세션 체크
//		Member member = (Member)session.getAttribute("member");
//		
//		if (member == null) {	// 로그인 하지 않은 경우
//			viewName = "shop/member/login";		// 로그인 폼으로 이동시킴.
//		} else {	// 로그인 한 경우
//			viewName = "shop/cart/list";
//		}
		// 3단계: 세션에 들어있는 cart 라는 key를 갖는 객체들을 List 형태로 바꿔서 jsp까지 전달.
		Map<Integer, Cart> cart = (Map)session.getAttribute("cart");
		
		List cartList = new ArrayList();	// 자바에서 map 은 순서가 없으므로 아래의 반복문을 이용하여 꺼내진 Cart DTO 를 리스트에 담자
		
		for(Map.Entry<Integer, Cart> entry : cart.entrySet()) {
			log.debug("키는 " +entry.getKey()+", 값은 "+entry.getValue());
			cartList.add(entry.getValue());
		}
		
		// 4단계: jsp 에서 보여질 결과 저장
		model.addAttribute("cartList" ,cartList);
		
		return "shop/cart/list";
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
	@PostMapping("/cart/add")
	@ResponseBody
	public ResponseEntity<ResponseMessage> addCart(Cart cart, HttpSession session) {
//		ResponseEntity<ResponseMessage> - 스프링에서 지원하는 HTTP 응답 전용 객체. head + body 를 구성해준다.
		
		// 클라이언트가 전송한 상품의 product_id, 갯수를 이용하여 Cart 생성하고 보관.
		// 그리고 이 생성된 Cart 인스턴스를 세션에 저장.
//		Cart cart = new Cart();
//		
//		cart.setProduct_id(product_id);
//		cart.setEa(product_id);	// 넘겨받을 예정
//		cart.setProduct_name(null);	// 상품명을 넘겨 받아야 함
//		cart.setPrice(product_id);	// 얘도 넘겨받아야 함
		
		// 장바구니에 담게 될 정보 중 누가? 에 해당하는 member_id 는 클라이언트의 브라우저로 넘겨받지 말고,(보안이슈)
		// 세션에서 꺼내서 Cart DTO 에 담자.
		Member member = (Member)session.getAttribute("member");
		log.debug("현재 세션의 Member DTO 안에 들어있는 member_id 는 {}", member.getMember_id());
		cart.setMember_id(member.getMember_id());
		
		log.debug("product_id = {}", cart.getProduct_id());
		log.debug("product_name = {}", cart.getProduct_name());
		log.debug("price = {}", cart.getPrice());
		log.debug("ea = {}", cart.getEa());
		
		// 세션에 cart 라는 키가 존재하는지를 확인해서, 없으면 새로 만들고 있으면 기존 것을 사용
		Map<Integer, Cart> map = null;
		if(session.getAttribute("cart")==null) {	// 없으면
			map = new HashMap<>();
			session.setAttribute("cart", map);
		} else {	// 있으면 기존거 꺼내기
			map = (Map)session.getAttribute("cart");	// 꺼내오기
		}
		// 꺼내온 맵 내에서도, 이미 등록된 상품이면 갯수만 증가시키고, 등록되지 않은 상품이면 새로 등록.
		Cart obj = (Cart)map.get(cart.getProduct_id());
		if(obj == null) {	// 아직 장바구니에 등록된 적 없는 상품.
			map.put(cart.getProduct_id(), cart);
		}else {		// 사용자가 이미 장바구닝 등록한 상품. 새로 등록하지 말고 넘겨받은 수 만큼 갯수를 누적.
			obj.setEa(obj.getEa()+cart.getEa()); 	// < 갯수 누적
		}
		
//		return "장바구니 등록 성공"; 	// @ResponseBody 애노테이션 없이 이렇게 리턴하면, 이게 웹페이지에 출력되는 게 아니라 서블릿이 등록 성공.jsp 를 매핑하려 한다.
//		또한 그냥 문자열이 아니라 JSON 의 형식을 갖춰서 응답해야 FM 이라는데? 이유는 잘 모르겠음.
		
		ResponseMessage msg = new ResponseMessage();
		msg.setMsg("장바구니에 상품이 담겼습니다.");
		// 이 시점에 Jackson 라이브러리를 개발자가 직접 사용할 필요 없다. @ResponseBody 에 의해 내부적으로 작동한다.
		
		return ResponseEntity.ok(msg);
	}
}




