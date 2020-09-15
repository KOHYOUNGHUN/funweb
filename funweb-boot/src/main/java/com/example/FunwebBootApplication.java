package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
//@Component 계열 애노테이션 이름: @Component, @Configuration, @Controller, @RestController, @Service, @Repository 등

@SpringBootApplication // @Component 계열 애노테이션이 붙은 클래스들을 스캔해서 스프링 빈으로 등록 // 최상위 패키지  // DI처리
//@MapperScan(value = {"com.example.mapper"} )

@EnableAspectJAutoProxy // 프로젝트 내의 @Aspect 클래스를 스캔해서 처리  // AOP처리 해당클래스 advice
//@EnableScheduling // 프로젝트 내의 @Scheduled 붙은 클래스들을 스캔해서 처리 // 활성화 

@MapperScan("com.example.mapper") // 매퍼 인터페이스를 구현한 객체를 스프링 빈으로 등록

public class FunwebBootApplication {

	public static void main(String[] args) {
		// http://localhost:8082/
		SpringApplication.run(FunwebBootApplication.class, args);
	}

}

// DI  의존성 
// 하나만 써서 돌려쓰는 싱글톤
// SELECT 쓰는 건 계속 만들어줘야댐 
// 