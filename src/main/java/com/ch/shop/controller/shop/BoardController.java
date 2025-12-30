package com.ch.shop.controller.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ch.shop.dto.Board;
import com.ch.shop.exception.BoardException;
import com.ch.shop.model.board.BoardService;
import com.ch.shop.model.board.BoardServiceImpl;

import lombok.extern.slf4j.Slf4j;

// 기존에 자체 제작한 MVC 프레임웤에선 모든 요청마다 일대일 대응하는 컨트롤러를 매핑했었지만, 
// 스프링 MVC 는 가령 게시판 1개에 대한 목록, 쓰기, 상세보기, 수정, 삭제 등에 대해 하나의 컨트롤러로 처리가 가능하다.
// 왜 Why? 스프링 MVC 는 모든 요청마다 1:1 대응하는 클래스 기반이 아닌, 메서드 기반이기 때문.

@Slf4j
@Controller    // 
public class BoardController {
	
	@Autowired
	private BoardService boardService;     // DI 를 위해 상위객체 보유
	
	// 글쓰기 폼 요청 처리
	// (현재 jsp가 WEB-INF 밑의 위치에 놓았는데, 보안상의 이유로 컨트롤러만 접근 가능하게 하려고 일부로 이렇게 놓았다.)
	// 근데 문제는 이렇게 하면 브라우저에서 직접 jsp에 접근이 불가능하므로, 이 컨트롤러의 메서드에서 /board/writer.jsp 를 매핑한다.
	@RequestMapping("/board/registform")
	public ModelAndView registForm() {
		// 3단계: 글쓰기 폼을 보여주는 거니까 DB와 연관이 없으므로 3단계에서 할 게 없다
		// 4단계: 마찬가지로 없다. 단순 글쓰기 폼 요청인데 할게 없음 진짜로 ㅇㅇ 그냥 만들어놓은 jsp만 주면 된다.
		// 단, 이 때 "/WEB-INF/views/board/write.jsp"    이렇게 직접 모든 키값을 주는 게 아니라, 
		// "board/write" 이렇게 접두어와 접미사 부분을 빼고 핵심부분만(개발자가 정한대로) 전달한다. 왜 why? 완전한 경로를 반환하면, 파일명이 바뀔 때 유지보수성이 떨어지므로
		// 따라서 개발자는 전체 파일 경로 중, 변하지 않는다고 생각하는 부분을 제외한 접두어, 접미어를 제외하고 핵심 알맹이라고 생각하는 부분을 전달.
		// 이 때, 하위 컨트롤러가 DispatcherServlet 에게 정보를 반환할 때는 String("") 형태도 가능하지만, ModelAndView 라는 객체를 이용할 수 있다.
		// ModelAndView 에 데이터를 담을 때에는 Model 객체에 자동으로 담기고, 접두어 접미어를 제외한 핵심 알맹이 문자열을 넣어둘 때는 View 객체에 담기는데,
		// ModelAndView 는 이 두 객체를 합쳐놓은 것.
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/write");	// 이렇게 알맹이라고 생각되는 부분만 View 로 세팅해서 전달.
		return mav;		// 
	}

	// 글 목록 페이지 요청 처리
	@RequestMapping("/board/list")
	public ModelAndView getList() {
		// 3단계 수행(DB에서 필요한 CRUD 처리)
		List list = boardService.selectAll();
		
		// 4단계: 결과 저장 (select 의 경우 결과가 있기 때문에 4단계 실행)
		// 현재 컨트롤러에서는 디자인을 담당하면 안되므로 디자인영역인 View 에 보여질 결과를 Request 객체에 저장. (Request 객체에)
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);		// 좌측 ""은 list.jsp에서 파라미터로 받는 key 값, 오른쪽 list는 위에서 변수명으로 정한 value 값
		mav.setViewName("board/list");
		
		return mav;
	}
	
	// 글쓰기 요청 처리
	// 메서드의 매개변수에 VO(Value Object)로 받을 경우 스프링에서 자체적으로 자동 매핑에 의해 파라미터값들을 채워 넣는다.
	// 단, 파라미터명과 VO의 변수명이 반드시 일치해야 한다.
	// DTO와 VO는 비슷하기는 하지만 DTO 는 테이블을 반영한 객체이다 보니 클라이언트에 노출되지 않도록 하는 것이 좋기 때문에, 
	// 단순히 클라이언트의 파라미터를 받는 것이 목적이라면, DTO 보다는 VO를 사용한다.
	@RequestMapping("/board/regist")
	public ModelAndView regist(Board board) {
		
		log.trace("제목은: " + board.getTitle());
		log.debug("작성자는: " + board.getWriter());
		log.info("내용은: " + board.getContent());
		
		ModelAndView mav = new ModelAndView();
		
		// 3단계: 모델 영역 객체에게 일 시키기
		try {
			boardService.regist(board);
			// 성공했을 시 메세지 관련 처리 (목록 페이지로 넘겨주면 됨)
			mav.setViewName("redirect:/board/list"); 	// 요청을 끊고, 새로 목록을 들여오라고 명령
		} catch (BoardException e) {
			log.error(e.getMessage());	// 로그에 찍히는 거니까 개발자만 안다.
			// 실패했을 시 메세지 관련 처리 (에러 페이지로 이동시키면 됨)
			mav.addObject("msg", e.getMessage());	// request.setAttribute("msg", e.getMessage()) 
			mav.setViewName("error/result");	// 개발자가 redirect 를 명시하지 않으면 스프링에서는 디폴트가 forwarding이다. 
			
		}
		return mav;
	}
	
	// 글 상세보기 요청 처리
	@RequestMapping("/board/detail")
	public String getDetail(int board_id, Model model) {	// 클라이언트가 전송한 파라미터명과 동일해야 매핑해줌(board_id). 이렇게 Model 과 View(String)를 나눌 수도 있다.
		Board board = boardService.select(board_id);
		model.addAttribute("board", board); 	// jsp에서의 key 값과 일치해야 함
		return "board/detail"; 	// 이렇게 String 으로 바로 줘도 된다. 암거나 가능.
	}
	
	
	// 글 수정 요청 처리
	
	@PostMapping("/board/edit")
	public String edit(Board board, Model model) {
		log.debug("title is " + board.getTitle());
		log.debug("writer is " + board.getWriter());
		log.debug("content is " + board.getContent());
		log.debug("board_id is " + board.getBoard_id());
		
		String viewName = null;
		
		try {
			boardService.update(board);
			viewName = "redirect:/board/detail?board_id="+ board.getBoard_id();
		} catch (BoardException e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());		// 에러 정보 저장
			viewName = "error/result";
		}
		
		return viewName;
	}
	
	// 글 삭제 요청 처리
	
	@GetMapping("/board/delete")
	public String delete(int board_id) {
		log.debug("삭제 요청 시 날아온 파라미터 값은: "+ board_id);
		
		boardService.delete(board_id);
		
		return "redirect:/board/list";
	}
	
	// 스프링의 컨트롤러 에서는 예외의 발생을 하나의 이벤트로 보고, 이 이벤트를 자동으로 감지하여
	// 에러를 처리할 수 있는 @ExceptionHandler 를 지원해줌
	@ExceptionHandler(BoardException.class)             // 현재 컨트롤러에 명시된 요청을 처리하는 모든 메서드 내에서 BoardException 이 발생하면 이를 자동으로 감지하여
	public ModelAndView handle(BoardException e) {   // 이 메서드를 호출해준다. 이 때 매개변수로 예외 객체의 인스턴스도 자동으로 넘겨준다.
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", e.getMessage());	// 저장
		mav.setViewName("error/result");
		return mav;
	}
	
	
}
