package com.ch.shop.controller.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ch.shop.model.topcategory.TopCategoryService;

// 모든 컨트롤러보다 앞서 실행되는 컨트롤러 정의.
// 목적: 현재 쇼핑몰의 모든 컨트롤러에서 TopCategory 목록을 매번 가져오는 코드가 중복되고 있으므로, 이를 해결하기 위함.
// 방법: @ControllerAdvice 를 명시한다. 메서드에 이 애노테이션을 붙이면, 다른 컨트롤러들이 실행되기 전에 앞서 실행되어 지는데, 
// 			주의할 점은 이 안에 정의한 모든 메서드가 먼저 동작하는 건 아니고, 아래의 3가지 애노테이션에 대해서만 효과가 발생한다.
//			1) @InitBinder: 컨트롤러로 전달되는 파라미터에 대해 개발자가 커스텀하고 싶을 때 사용
//			2) @ModelAttribute: Model 객체에 model.setAttribute("topList", topList); 의 효과를 낼 수 있는 애노테이션
//			3) @ExceptionHandler: 

@ControllerAdvice	  // 다른 컨트롤러보다 앞서서 동작하는 컨트롤러
public class GlobalController {
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	// 아래의 메서드는 위에 3가지 애노테이션을 명시하지 않았으므로, 먼저 동작하는 효과가 없다.
	public void test() {
	}
	
	// 쇼핑몰의 상위 카테고리를 저장해놓기
	@ModelAttribute("topList")	// 이 애노테이션이 하단 4단계 주석의 효과를 낸다.
	public List getTopCategoryList() {
//		List topList = topCategoryService.getList();	// 3단계: 일 시키기
//		model.addAttribute("topList", topList);	// 4단계: 결과 페이지로 가져갈 것이 있다 > 저장		
		
		return topCategoryService.getList();
	}
}
