package com.ch.shop.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch.shop.dto.Size;
import com.ch.shop.model.size.SizeService;

// 관리자 페이지에서 사이즈와 관련된 요청을 처리하는 컨트롤러
@Controller
public class SizeController {
	
	@Autowired
	private SizeService sizeService;
	
	// 모든 레코드 가져오기 (JSON)
	@GetMapping("/size/list") // admin-dispatcher가 /admin/* 이므로 실제 호출은 /admin/size/list
	@ResponseBody
	public List<Size> getList() {
		return sizeService.getList();
	}
}


