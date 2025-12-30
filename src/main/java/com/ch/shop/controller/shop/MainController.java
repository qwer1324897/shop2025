package com.ch.shop.controller.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ch.shop.model.topcategory.TopCategoryService;

// 유저가 이용하게 되는 쇼핑몰의 메인페이지와 관련된 요청을 처리하는 컨트롤러 
@Controller
public class MainController {
	
	// 쇼핑몰이건, 관리자모드이건 MVC 로 개발이 되어 있다면, 모델 영역은 재사용이 가능하다.
	// 따라서 관리자모드에서 쇼핑몰의 상위 카테고리와 관련되어 TopCategoryService 를 이용하였듯
	// 여기서도 TopCategoryService 를 이용해보자
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	// 메인 요청 처리
	@GetMapping("/")
	public ModelAndView getMain() {
		// 3단계: 여러 DB 연동 업무 중, 상위 카테고리 가져오기
		List topList = topCategoryService.getList();
		
		// 4단계: 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("topList", topList);
		mav.setViewName("shop/index");
		
		return mav;
	}
		
}
