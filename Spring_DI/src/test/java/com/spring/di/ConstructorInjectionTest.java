package com.spring.di;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.di.cnsrt.ConstructorInjectionMainClass;
import com.spring.di.cnsrt.ConstructorInjectionSubClass;

public class ConstructorInjectionTest {

	//스프링 컨테이너 테스트
	@Test
	void constructorInjectionContainerTest() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringDiApplication.class);
		ConstructorInjectionMainClass mainClass = context.getBean(ConstructorInjectionMainClass.class);
		mainClass.run();
	}
	
	//순수 자바 테스트
	@Test
	void constructorInjectionJavaTest() {
		ConstructorInjectionMainClass mainClass = new ConstructorInjectionMainClass(new ConstructorInjectionSubClass());
		mainClass.run();
	}
}
