package com.spring.di;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.di.field.FieldInjectionMainClass;

public class FieldInjectionTest {


	//스프링 컨테이너 테스트
	@Test
	void fieldInjectionContainerTest(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringDiApplication.class);
		FieldInjectionMainClass mainClass = context.getBean(FieldInjectionMainClass.class);
		mainClass.run();
	}
	
	//순수 자바 테스트
	//필드 주입은 스프링 컨테이너가 아니면 주입할 방법이 없으므로 의존 객체가 null이기 때문에 NPE발생
	@Test
	void fieldInjectionJavaTest() {
		FieldInjectionMainClass mainClass = new FieldInjectionMainClass();
		mainClass.run();
	}
}
