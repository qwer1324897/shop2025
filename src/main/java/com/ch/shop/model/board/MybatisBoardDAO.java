package com.ch.shop.model.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch.shop.dto.Board;
import com.ch.shop.exception.BoardException;

import lombok.extern.slf4j.Slf4j;

// board 테이블에 대한 CRUD 를 수행하되, 직접 쿼리문을 작성하지 않고 mybatis-spring 을 이용한다.
@Repository    // 이렇게 Repository 어노테이션을 해 놓으면, 스프링이 자동으로 스캔해서 인스턴스를 자동으로 생성해주고 Bean 컨테이너로 관리함.
@Slf4j
public class MybatisBoardDAO implements BoardDAO{

	// 스프링에서는 DI 를 적극 활용해야 하므로, 필요한 객체의 인스턴스를 직접 생성하면 안 되고
	// 스프링 컨테이너로부터 주입(Injection) 받아야 한다.
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;	// 재정의한 것이 아니므로 그냥 사용.
	
	// setter 주입 준비
//	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
//		this.sqlSessionTemplate = sqlSessionTemplate;
//	}     이 행위를 상단의 @Autowired 가 대신 수행.
	
	public void insert(Board board) throws BoardException{
		try {
			sqlSessionTemplate.insert("Board.insert", board);
		} catch (Exception e) {
			log.error("게시물이 등록되지 않았습니다");
			// 아래에서 에러를 일으킨 목적은, 예외를 처리하기 위함이 아니라 외부에 책임을 전가시키기 위함. 따라서 throws.
			// 여기서 예외를 처리하면 계속 무한 try catch or 프로그램 비정상 종료
			throw new BoardException("게시물 등록 실패", e);
		}
	}

	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("Board.selectAll");
	}

	@Override
	public Board select(int board_id) {
		return sqlSessionTemplate.selectOne("Board.select", board_id);
	}

	@Override
	public void update(Board board) throws BoardException{
		try {
			sqlSessionTemplate.update("Board.update", board);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException("수정 실패", e); 	// 일부러 에러 발생시킴(throws 위해)
		}
	}

	@Override
	public void delete(int board_id) throws BoardException{
		try {
			sqlSessionTemplate.delete("Board.delete", board_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException("삭제 실패", e);
		}
	}
	
}
