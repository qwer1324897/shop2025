package com.ch.shop.model.color;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorServiceImpl implements ColorService {

	@Autowired     // 스프링 컨테이너가 관리하던 Bean 을 여기로 주입 요청
	private ColorDAO colorDAO;		// 느슨하게(의존성이 낮게) 보유하기 위해 MybatisColorDAO가 아닌, 상위 자료형(인터페이스)을 보유하자.
	
	@Override
	public List getList() {
		return colorDAO.selectAll();
	}


}
