package com.bean.BeanFactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class BeanFactoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeanFactoryApplication.class, args);
		System.out.println("Example for BeanFactory");

		BeanFactory factory = new ClassPathXmlApplicationContext("beans.xml");
		Student student = (Student) factory.getBean("student");

		System.out.println(student);

	}

}
