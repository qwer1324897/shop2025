package com.ch.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 이 컨트롤러는 관리자 메인 관련된 요청을 처리하는 하위 컨트롤러임 
@Controller    // ComponentScan 에 의해 자동으로 인스턴스를 생성해준다. 그러기 위해선 개발자가 이 클래스가 검색될 수 있도록 제대로 된 패키지 명을 등록해야 함.
public class AdminController {
	
	// 관리자 모드의 메인인 대시보드 요청을 처리
	@GetMapping("/main")
	public String main() {
		
		// 3단계: 현재 없음
		// 4단계: 현재 없음
		
		return "admin/index";
	}
}
