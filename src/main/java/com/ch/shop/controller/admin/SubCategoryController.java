package com.ch.shop.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch.shop.dto.SubCategory;
import com.ch.shop.model.subcategory.SubCategoryService;

import lombok.extern.slf4j.Slf4j;

// 쇼핑몰의 하위 카테고리에 대한 요청을 처리하는 하위 컨트롤러
@Controller
@Slf4j
public class SubCategoryController {
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	// 목록 요청 처리
	// 주의) 클라이언트가 비동기 요청을 시도할 경우, 서버는 절대 Html 문서를 원하는 것이 아니므로
	// 데이터를 보내줘야 한다. 개발 프로그래밍에서 표준적으로 많이 사용하는 형식이 JSON 문자열이므로 JSON 사용
	@GetMapping("/subcategory/list")
	@ResponseBody     // 이 메서드에서 리턴한 값은 매핑같은 거 하지말고 응답정보로 바로 사용하도록 하는 어노테이션. 이 때, Json MessageConvert 를 자동 적용한다.
	public List<SubCategory> getList(int topcategory_id) {
		
		List subList = subCategoryService.getList(topcategory_id);
		
		log.debug("하위 카테고리는 " + subList);
		
		// 자바 객체를 JSON 문자열로 바꿔주는 API 인 Jackson 을 이용하여 자바 객체를 JSON 형태로 전송.
		
		// 클라이언트가 비동기 요청을 시도했으므로, 서버측의 하위 컨트롤러는 jsp 매핑을 해선 안 된다. 그냥 데이터만 요청한건데 html 을 왜 보냄 데이터만 보내면 된다.
		// 따라서 return 시킨 정보에 대해 jsp 매핑을 하는 게 아니라, 반환값 그 자체를 응답정보로 사용하라고 하면 된다.
		return subList;
		
	}
	
}


