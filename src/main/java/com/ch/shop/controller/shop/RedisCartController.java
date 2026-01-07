package com.ch.shop.controller.shop;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch.shop.dto.Cart;
import com.ch.shop.dto.Member;
import com.ch.shop.dto.ResponseMessage;
import com.ch.shop.exception.CartException;
import com.ch.shop.model.order.CartService;

import lombok.extern.slf4j.Slf4j;

// 세션 기반이 아닌, 메모리 기반 NOSQL DB 인 Redis 로 장바구니 요청을 처리하는 컨트롤러
@Controller
@Slf4j
public class RedisCartController {
	
	@Autowired
	private CartService cartService;

	// 장바구니 등록을 비동기로 처리
	@PostMapping("/cart/regist")
	@ResponseBody
	public ResponseEntity<ResponseMessage> regist(Cart cart, HttpSession session) {
		
		Member member = (Member)session.getAttribute("member");	// member id 는 cart 에 없으므로 세션에서 끌어옴.
		cart.setMember_id(member.getMember_id());	// 누구의 장바구니인지를 알기 위함
		
		// Redis 에 저장할 형식 [cart:member_id(key) : product_id(field) : ea(value)] 중에서 key 에 들어갈 값.
		log.debug("member_id 는 " + cart.getMember_id());
		log.debug("product_id 는 " + cart.getProduct_id());
		log.debug("ea 는 " + cart.getEa());
		
		// 3단계: 일 시키기
		cartService.regist(cart);
		
		ResponseMessage message = new ResponseMessage();
		message.setMsg("장바구니 등록 성공");
		
		return ResponseEntity.ok(message);
	}
	
	// 컨트롤러의 모든 메서드 중, 예외가 발생할 경우 무조건 아래의 핸들러 메서드로 실행부가 진입
	@ExceptionHandler(CartException.class)
	public ResponseEntity<ResponseMessage> handle(CartException e) {
		
		ResponseMessage message = new ResponseMessage();
		message.setMsg("장바구니 등록 실패");
		// 서버에서 특별히 에러 상태코드를 보내지 않으면, 클라이언트 ajax 측에서 성공인 success 가 동작할 수 있으므로,
		// header 값에 에러 수준의 상태 코드를 보내자
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
	}
}















