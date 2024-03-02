package com.security.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		//인가 처리 로직 : 해당 경로에 대한 접근권한 부여
		http.authorizeHttpRequests(
				(auth) -> auth
				.requestMatchers("/", "/login", "/join", "joinProc").permitAll()
				.requestMatchers("/admin").hasRole("ADMIN")
				.requestMatchers("my/**").hasAnyRole("ADMIN", "USER")
				.anyRequest().authenticated()
		);
		
		//접근권한이 없을 경우 로그인 페이지로 리다이렉트
		http.formLogin((auth) -> auth.loginPage("/login")
				.loginProcessingUrl("/loginProc")
				.permitAll()
		);
		
		//개발환경에서는 csrf 토큰을 검증하지 않도록 처리
		http.csrf((auth) -> auth.disable());
		
		
		return http.build();
	}
	
	//단방향 암호화 처리를 위한 객체
	@Bean
	public BCryptPasswordEncoder bcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
