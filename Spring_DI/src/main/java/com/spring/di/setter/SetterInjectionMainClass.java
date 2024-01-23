package com.spring.di.setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetterInjectionMainClass {

	private SetterInjectionSubClass bean;

	//주입할 빈이 없으면 해당 메소드를 호출하지 않는다.
	@Autowired(required = false)
	public void setBean(SetterInjectionSubClass bean) {
		this.bean = bean;
	}
	
	public void run() {
		System.out.println("SetterInjectionMainClass Regist Success");
		bean.run();
	}
}
