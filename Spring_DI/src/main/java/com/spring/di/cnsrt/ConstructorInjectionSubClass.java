package com.spring.di.cnsrt;

import org.springframework.stereotype.Component;

@Component
public class ConstructorInjectionSubClass {

	// MainClass와 순환 참조시 오류를 발생시켜 애플리케이션 실행을 할 수 없음
//	private ConstructorInjectionMainClass mainBean;
	
//	public ConstructorInjectionSubClass(ConstructorInjectionMainClass mainBean) {
//		this.mainBean = mainBean;
//	}

	public void run() {
//		mainBean.run();
		System.out.println("Constructor Injection Running...");
	}
}
