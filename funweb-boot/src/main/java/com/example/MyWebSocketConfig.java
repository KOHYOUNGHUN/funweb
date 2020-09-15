package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.example.websocket.MyTextWebSocketHandler;

@Configuration
@EnableWebSocket // 웹소켓 서버 기능 활성화하기 
public class MyWebSocketConfig implements WebSocketConfigurer {
	
	@Autowired
	private MyTextWebSocketHandler webSocketHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// "/chatting"는 소켓연결을 위한 주소로서 http 연결이 아닌 ws 연결이 되야 함!
		registry.addHandler(webSocketHandler, "/chatting")  // 웹소켓 핸들러 등록
				.addInterceptors(new HttpSessionHandshakeInterceptor())
				// 세션에 저장되어있는 데이터를  인터셉터가 웹소켓 세션으로 옮겨줌 
				// HttpSession에 있는 attributes 값들을 webSocketSession으로 복사해줌
				.setAllowedOrigins("*");
	}

	// 스프링이 관리해야될 객체를 단순한 생성자 호출로 할 수 없을 때 ( 메소드방식으로 설정이 필요할 때 )
	// 최종적으로 리턴한 객체가 스프링 Bean이 됌
	// 쿼츠 스케줄러와 충돌 피하기 위해 삽입

	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();

	    scheduler.setPoolSize(2);
	    scheduler.setThreadNamePrefix("scheduled-task-");
	    scheduler.setDaemon(true);

	    return scheduler;
	}

}
