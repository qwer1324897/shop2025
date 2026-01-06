package com.ch.shop.model.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch.shop.dto.Member;
import com.ch.shop.exception.MemberException;
import com.mysql.cj.log.Log;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
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
			int updated = sqlSessionTemplate.update("Member.update", member);
			if(updated > 0) {
				log.debug("회원정보 업데이트 함");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException("회원정보 수정 실패", e);
		}
	}

}
