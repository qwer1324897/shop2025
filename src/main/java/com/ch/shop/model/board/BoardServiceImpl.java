package com.ch.shop.model.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.ch.shop.dto.Board;
import com.ch.shop.exception.BoardException;

// 서비스는 모델 영역에서 여러 모델 객체들에게 일을 시키는 역할을 수행
// 대표적 업무) 여러 DAO 들에게 일을 시키고, 트랜잭션 상황에서 트랜잭션을 처리할 의무를 가진 객체.
// 만일 서비스의 존재가 없을 경우, 컨트롤러가 서비스의 역할을 수행하게 되므로, 이 때부터 컨트롤러의 코드에 모델영역의 업무가 혼재되어
// MVC에 원칙 위배된다. 모델 코드를 재사용할 수 없어지고 유지보수성이 떨어진다.
@Service    // 이렇게 Service 어노테이션을 붙이면 new로 메모리 올릴 필요 없이 ComponentScan 의 대상이 되어 자동으로 인스턴스를 올리고, Bean 컨테이너에서 관리해준다.
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDAO boardDAO;
	
	// setter 를 정의하여 외부에서 DAO 의 인스턴스를 넘겨받자. 이 때 new를 하지 않고 주입을 받는 이유는 의존성을 약화시키기 위해.
//	public void setBoardDAO(BoardDAO boardDAO) {
//		this.boardDAO = boardDAO;
//	}    이 행위를 상단의 @Autowired 가 대신 수행.
	
	// DAO 에게 글 등록 시키기
	public void regist(Board board) throws BoardException {
		// 에러가 났음을 유저가 알아야 하므로, 즉 예외 전달만이 목적이므로 여기서 예외를 잡지 않고 throws 로 던진다.ㄹ
		boardDAO.insert(board);
	}

	@Override
	public List selectAll() {
		return boardDAO.selectAll();
	}

	@Override
	public Board select(int board_id) {
		return boardDAO.select(board_id);
	}

	@Override
	public void update(Board board) throws BoardException{	// Controller 까지 예외 전달. 안해도 문제 없지만, mybatisDAO에서 트라이캐치를 했기 때문에 보드까지 전달 안하면 무의미.
		boardDAO.update(board);
	}

	@Override
	public void delete(int board_id) throws BoardException{
		boardDAO.delete(board_id);
	}
}
