package com.spring.di.setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetterInjectionSubClass {
	
//	private SetterInjectionMainClass mainBean;

//	@Autowired
//	public void setMainBean(SetterInjectionMainClass mainBean) {
//		this.mainBean = mainBean;
//	}

	public void run() {
		System.out.println("Setter Injection Running...");
	}
}
