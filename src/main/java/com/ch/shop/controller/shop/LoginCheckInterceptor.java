package com.ch.shop.controller.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
 로그인 한 회원에게만 제공되는 서비스를 로그인을 하지 않은 유저가 접근할 경우, 로그인 폼으로 강제하는 처리를 하기 위해선
 세션 체크코드를 작성해야 한다. 근데 이걸 회원에게만 제공되는 요청을 처리하는 모든 컨트롤러마다 세션 체크코드를 넣어버리면
 코드 중복이 발생한다. >> 유지보수성이 떨어진다.
 따라서 유지보수성을 위해 스프링에서 제공하는 인터셉터를 이용한다.
 */

public class LoginCheckInterceptor implements HandlerInterceptor{	// 스프링 자체 인터페이스. 우리가 만든게 아니다.

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 이름에서 알 수 있다시피, 서블릿이 핸들러로 컨트롤러에게 요청하기 전에(pre) 동작하는 매서드이다.
		
		// 현재 요청에 연계된 세션 얻기
		HttpSession session = request.getSession();
		
		// 로그인 하지 않았을 경우, 가던 길 가게 해주는 게 아니라 로그인 폼으로 강제전환.
		if (session == null || session.getAttribute("member")==null) {	// 세션이 없거나, 세션이 있더라도 member 가 없으면 false.
			
			String asyncHeader = request.getHeader("X-Requested-With");
			
			if(asyncHeader!=null && asyncHeader.equals("XMLHttpRequest")) {		// 비동기로 요청이 들어온 경우, 응답메세지로 처리(JSON)
				response.setContentType("application/json; charset=UTF-8");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);	// 서버의 응답 상태 코드
				response.getWriter().write("{\"msg\" : \"로그인이 필요한 서비스입니다.\"}");
				
			} else {	// 동기로 요청이 들어온 경우, 응답페이지로 처리
				response.sendRedirect("/member/loginform");		// << 이건 동기로 들어왔을 때의 처리					
			}
			return false;
		}
		// 원래의 요청을 그대로 진행하고 싶다면 true, 진행을 막으려면(회원 유저에게만 제공하려면) false.
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
