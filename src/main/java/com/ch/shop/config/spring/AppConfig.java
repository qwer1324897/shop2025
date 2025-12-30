package com.ch.shop.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.ch.shop.test.food.Cook;
import com.ch.shop.test.food.FriPan;
import com.ch.shop.test.food.Induction;
import com.ch.shop.test.school.Bell;
import com.ch.shop.test.school.BellAspect;
import com.ch.shop.test.school.Student;

// 기존에는 DI를 구현하기 위해 개발자가 필요로 하는 자바의 클래스(빈 = bean)들을 xml 에 정의해왔음. 
// 그러나 이제는 자바 클래스로 정의한다.
// 따라서 아래의 클래스는 로직을 작성하기 위함이 아니라 오직 개발자가 사용하고 싶은 클래스들의 명단을 작성하기 위함이다.
// 이렇게 등록된 클래스 각각을 Java EE 에서는 bean 이라 부른다.
// 또한 아래의 클래스 안에 @Bean 을 등록 해 놓으면 스프링 프레임웤이 자동으로 인스턴스화 시켜 메모리에 모아놓는데
// 이 때 이 역할을 수행하는 스프링의 객체를 ApplicationContext 라 하며 스프링 컨테이너 라고 부르기도 한다.

@Configuration 		// 아래의 클래스는 설정용 클래스임을 선언(로직 X)
@EnableAspectJAutoProxy
public class AppConfig {

	// 애플리케이션에서 사용할 모든 객체들을 등록하자
	@Bean
	public FriPan friPan() {
		return new FriPan();
	}
	
	@Bean
	public Induction induction() {
		return new Induction();
	}
	
	// 아래와 같이 Bean 들간의 관계를 표현해 놓은 것을 weaving (엮기) 한다고 함.
	@Bean
	public Cook cook(Induction pan) {
		return new Cook(pan);
	}
	
	@Bean
	public Bell bell() {
		return new Bell();
	}
	
	@Bean
	public Student student() {
		return new Student();
	}
	
	@Bean
	public BellAspect bellAspect(Bell bell) {
		return new BellAspect(bell);
	}
}















