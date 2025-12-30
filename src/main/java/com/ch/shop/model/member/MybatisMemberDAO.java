package com.ch.shop.model.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch.shop.dto.Member;
import com.ch.shop.exception.MemberException;

@Repository
public class MybatisMemberDAO implements MemberDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Member findByProvider(Member member) {
		return sqlSessionTemplate.selectOne("Member.findByProvider", member);
	}	
	
	@Override
	public void insert(Member member) throws MemberException{
		try {
			sqlSessionTemplate.insert("Member.insert", member);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException("회원 insert 실패", e);
		}
	}

	@Override
	public void update(Member member) throws MemberException{
		try {
			sqlSessionTemplate.update("Member.update", member);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException("회원정보 수정 실패", e);
		}
	}

}
