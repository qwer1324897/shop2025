package com.ch.shop.model.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ch.shop.dto.Provider;

@Service
public class ProviderServiceImpl implements ProviderService{

	@Autowired
	private ProviderDAO providerDAO;
	
	@Override
	public List selectAll() {
		return providerDAO.selectAll();
	}

	@Override
	public Provider selectByName(String provider_name) {
		return providerDAO.selectByName(provider_name);
	}

}
