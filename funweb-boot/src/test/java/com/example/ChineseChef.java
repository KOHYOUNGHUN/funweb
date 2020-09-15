package com.example;

import org.springframework.stereotype.Component;


@Component
// 객체생성 가능한 클래스에 선언가능 (@Component)
// spring bean  에노테이션붙은거, 상속받은 클래스 포함 
// spring 컨테이너 / 이걸 기반으로  객체 생성 
// 스프링이 알아서 조립 
// 객체간의 관계 - 인터페이스 사용 - 결합도 낮춤 

public class ChineseChef implements Chef {

	@Override
	public void doCook() {
		System.out.println("중국음식을 요리합니다.");
	}

}
