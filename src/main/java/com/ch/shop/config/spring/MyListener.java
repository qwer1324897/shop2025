package com.ch.shop.config.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lombok.extern.slf4j.Slf4j;

// 서버가 가동될 때 동작하는 객체
@Slf4j
public class MyListener implements ServletContextListener{
	
	// 애플리케이션이 시작될 때 호출되는 메서드
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("애플리케이션 시작!");
		// 스프링 프레임웤에선 이 시점에 ServletContext 에게 AnnotationConfigApplicationContext.
		// 즉 스프링 컨테이너를 생성하여 비즈니스 로직이 들어있는 Model 영역과 관련된 Bean 들을 생성하여 관리하게 만든다.
		ServletContext application = sce.getServletContext();
		String contextClass = application.getInitParameter("contextClass");
		log.debug("읽어들인 초기화 파라미터 값은" + contextClass);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// context 자바의 설정파일을 읽어들여, Bean 들의 인스턴스를 생성하고 관리했을 것.
		application.setAttribute("contextContext", context);
	}
	
	// 애플리케이션이 종료될 때 호출되는 메서드
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("애플리케이션 종료!");
	}
}
