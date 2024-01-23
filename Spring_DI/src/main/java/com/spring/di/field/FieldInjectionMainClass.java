package com.spring.di.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FieldInjectionMainClass {

	@Autowired
	private FieldInjectionSubClass bean;
	
	public void run() {
		System.out.println("FieldInjectionMainClass Regist Success");
		bean.run();
	}
}
