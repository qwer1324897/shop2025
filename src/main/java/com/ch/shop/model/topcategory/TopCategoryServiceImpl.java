package com.ch.shop.model.topcategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service     // componentScan의 대상이 되고 자동으로 메모리에 올려줌
public class TopCategoryServiceImpl implements TopCategoryService{

	@Autowired
	private TopCategoryDAO topCategoryDAO;
	
	@Override
	public List getList() {
		return topCategoryDAO.selectAll();
	}

}
