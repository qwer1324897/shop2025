package com.ch.shop.config.spring;

import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

// 이 클래스는 로직을 작성하기 위함이 아니라, 애플리케이션에서 사용할 Bean(객체)들 과 그 관계들(weaving)을 명시하기 위한 설정 목적의 클래스.
// 실제 쇼핑몰의 일반 유저들이 보게 되는 애플리케이션 쪽 빈들을 관리한다.

@Configuration    // 이 어노테이션을 붙이는 것이 곧 설정용 클래스다 라는 선언.
@EnableWebMvc    // 필수 설정. 스프링이 지원하는 MVC 프레임웤을 사용하기 위한 어노테이션

// 일일이 빈으로 등록할 필요가 없는 많이 알려진 빈들을 가리켜 스프링에서는 컴포넌트라 부른다.
// 또한 이 컴포넌트들은 패키지 위치만 설정 해 놓으면 스프링이 알아서 찾아내서 (검색) 인스턴스를 자동으로 만들어 준다.

// MVC 에서의 Controller 는 @Controller 를 붙이면 되고, MVC 에서의 DAO 는 @Repository 를 붙이면 된다.
// MVC 에서의 DAO 는 @Service 를 붙인다  => 이러면 자동으로 메모리에 올려줌.
// MVC 에서의 특정 분류가 딱히 없음에도 자동으로 메모리에 올리고 싶다면, @Component 를 붙이면 된다.

@ComponentScan(basePackages = {"com.ch.shop.controller.admin"})   // admin 컴포넌트가 연결되어있기 때문에 하단에 코드가 없어도 의미가 있는 클래스이다.
// 해당 컨트롤러에 @Controller 어노테이션이 선언되어 있으면 컴포넌트스캔으로 Bean을 자동생성
public class AdminWebConfig extends WebMvcConfigurerAdapter{
	
	// 아파치 파일 업로드 컴포넌트를 Bean 으로 등록. 이걸 하면 MultipartFile[] 으로 파일을 저장할 수 있다. 스프링의 기능.
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		return resolver;
	}
	
}




