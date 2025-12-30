package com.ch.shop.model.board;

import java.util.List;
import com.ch.shop.dto.Board;

// 이 객체를 보유하게 될 컨트롤러가 너무 정확하고 구체적인 서비스 객체를 보유하면,
// 추후 그 그 서비스 객체에 유지보수 및 변경이 필요할 경우 의존하고 있던 컨트롤러가 영향을 받는다.
// 따라서 DI 를 준수하기 위해 상위 인터페이스를 만들어 서비스 객체를 구현할 필수 메서드들을 여기에 정의한다.
public interface BoardService {
	
	public void regist(Board board);
	public List selectAll();
	public Board select(int board_id);
	public void update(Board board);
	public void delete(int board_id);
	
}
