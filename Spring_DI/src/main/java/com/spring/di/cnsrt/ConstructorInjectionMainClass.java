package com.spring.di.cnsrt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConstructorInjectionMainClass {

	private ConstructorInjectionSubClass bean;

	//생성자 주입은 주입 할 빈이 존재하지 않는경우 실행할 수 없다.
//	@Autowired(required = false)
	public ConstructorInjectionMainClass(ConstructorInjectionSubClass bean) {
		this.bean = bean;
	}
	
	public void run() {
		System.out.println("ConstructorInjectionMainClass Regist Success");
		bean.run();
	}
	
}
