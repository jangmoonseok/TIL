package com.spring.di;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.di.setter.SetterInjectionMainClass;
import com.spring.di.setter.SetterInjectionSubClass;

@SpringBootTest
public class SetterInjectionTest {

	//스프링 컨테이너 테스트
	@Test
	void setterInjectionContainerTest(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringDiApplication.class);
		SetterInjectionMainClass mainClass = context.getBean(SetterInjectionMainClass.class);
		mainClass.run();
	}
	
	//순수 자바 테스트
	//setter메소드를 public으로 열어놔야만 순수 자바코드로 테스트가 가능하다.
	@Test
	void setterInjectionJavaTest() {
		SetterInjectionMainClass mainClass = new SetterInjectionMainClass();
		mainClass.setBean(new SetterInjectionSubClass());
		
		mainClass.run();
	}
}
