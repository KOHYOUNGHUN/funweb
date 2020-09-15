package com.example.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Aspect // 횡단관점 // 최상위클래스의 enableaspect가 이걸 AOP처리함
@Component // 의존성 주입 대상 
@Log
public class LogAdvice { // Advice: 여러 주요로직에 적용될 보조기능 클래스
	// 앞의 * 리턴타입, 상관없음 
	@Before("execution(public * com.example.service.SampleService.*(..))") // 개수 상관없으면 .. / 2개면 (*,*)
	public void logBefore() {
		log.info("=========================");
		
	}
	
	@Before("execution(* com.example.service.SampleService.doAdd(String, String)) && args(str1, str2)") // args 인자값 
	public void logBeforeWithParam(String str1, String str2) {
		log.info("str1 : "+str1);
		log.info("str2 : "+str2);

	}
	
	@AfterThrowing(pointcut = "execution(* com.example.service.SampleService.*(..))", throwing ="exception") //  after 타겟 메서드 호출 뒤 
	public void logException(Exception exception) {
		log.info("Exception...!!!");
		log.info("exception : " + exception);
	}
	
	
	@Around("execution(* com.example.service.SampleService.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) throws Throwable {
	
		long startTime = System.currentTimeMillis();
		
		
		log.info("Target : " + pjp.getTarget());
		log.info("Param : " + Arrays.toString(pjp.getArgs()));
		
		Object result = null;
		
			result = pjp.proceed();  // doAdd() 호출함
	
		long endTime = System.currentTimeMillis();
		
		log.info("method run time : " + (endTime - startTime) + "ms");
		
		return result;
	
	}
}