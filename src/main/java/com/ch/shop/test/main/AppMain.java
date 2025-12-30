package com.ch.shop.test.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ch.shop.config.spring.AppConfig;
import com.ch.shop.test.food.Cook;
import com.ch.shop.test.school.Student;

public class AppMain {
	
	public static void main(String[] args) {
		
		// AnnotationConfigApplicationContext는 개발자가 설정해놓은 클래스를 읽어드려야 하므로, 생성자의 매개변수로 전달
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		// 위의 생성자가 호출되는 순간, 개발자가 설정파일에 @Bean 으로 명시해 놓은 객체들이 인스턴스를 생상하여 보관하게 된다
		// 따라서 빈 컨테이터라 부른다.
		
		// 스프링의 ApplicationContext 는 개발 방법에 따라 여러가지 하위 자료형을 지원해 주는데,
		// 예) Bean 설정파일이 xml 일 경우 ClasspathXmlApplicationConext 
//				 Bean 설정 파일이 자바의 클래스인 경우, AnnotaionConfigApplicationContext
		
		Cook cook = (Cook)applicationContext.getBean("cook");
		cook.makeFood();
		
		// 학생 객체에 원하는 동작을 시켜 벨을 울려보자
		Student student = (Student)applicationContext.getBean("student");
		student.gotoSchool();
		student.study();
		student.rest();
		student.haveLunch();
		student.goHome();
		
	}
}

// 우리가 메모리에 올리고 싶은 객체를 스프링이 알아야 하므로, 두가지 중 하나를 선택
// 1. xml 파일에 필요한 모든 객체를 정의하는 방법 (요즘 안 씀)
// 2. xml 파일이 아닌, 필요한 객체들을 자바의 클래스로 정의하는 방법 (현업에선 이걸로 쓴다)