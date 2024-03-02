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
		
		//�ΰ� ó�� ���� : �ش� ��ο� ���� ���ٱ��� �ο�
		http.authorizeHttpRequests(
				(auth) -> auth
				.requestMatchers("/", "/login", "/join", "joinProc").permitAll()
				.requestMatchers("/admin").hasRole("ADMIN")
				.requestMatchers("my/**").hasAnyRole("ADMIN", "USER")
				.anyRequest().authenticated()
		);
		
		//���ٱ����� ���� ��� �α��� �������� �����̷�Ʈ
		http.formLogin((auth) -> auth.loginPage("/login")
				.loginProcessingUrl("/loginProc")
				.permitAll()
		);
		
		//����ȯ�濡���� csrf ��ū�� �������� �ʵ��� ó��
		http.csrf((auth) -> auth.disable());
		
		
		return http.build();
	}
	
	//�ܹ��� ��ȣȭ ó���� ���� ��ü
	@Bean
	public BCryptPasswordEncoder bcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
