package com.ch.shop.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch.shop.dto.Color;
import com.ch.shop.model.color.ColorService;

// 관리자 페이지에서 색상과 관련된 요청을 처리하는 컨트롤러

@Controller
public class ColorController {
	
	@Autowired
	private ColorService colorService;
	
	// 모든 레코드 가져오기
	@GetMapping("/color/list")    // /admin/color/list 이게 아닌 이유는 xml 파일 매핑할때 admin~ 으로 매핑했기 때문에 
	@ResponseBody
	public List<Color> getList() {	// 비동기방식으로 만들고 있기 때문에 서버측에서는 jsp로 랜더링 결과인 html 문서를 응답하면 안 되고 JSON 문자열로 응답.
													// 이 때, 원래는 개발자가 String 응답 문자열을 보내야 하지만 번거로우니까 자바 객체를 JSON 문자열로 변환해주는 API 를 이용.
													// 이걸 사용하려면 @ResponseBody 어노테이션을 주고 Spring 설정(지금의 경우, RootConfig)에다가 Converter 를 사전에 등록해 놓아야 한다.
		
		return colorService.getList();	// 이러면 이제 JSON 문자열이 반환됨.
	}
	
}
