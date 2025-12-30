package com.ch.shop.model.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch.shop.dto.Member;
import com.ch.shop.util.MailSender;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private MailSender mailSender;
	
	@Transactional
	public void registOrUpdate(Member member) {
		
		// 회원이 존재하는지 먼저 확인
		Member obj = memberDAO.findByProvider(member);
		
		if(obj==null) {
			memberDAO.insert(member);	// << 회원가입이 안 되어 있을 경우만.	
			mailSender.send(member.getEmail(), "회원샵 가입을 축하드립니다!", "감사합니다.");
			log.debug("신규 회원 가입 처리 완료");
			// 이메일 발송 예정 (카카오의 경우만 제외)
		} else {
			// sns 회원의 경우, 자신의 프로필을 변경할 수 있기 때문에 우리의 mysql 도 그 정보에 맞게 동기화시켜야 한다.
			member.setMember_id(obj.getMember_id());
			memberDAO.update(member);
			log.debug("기존 회원 업데이트 처리 완료");
		}
		
		
	}

}
