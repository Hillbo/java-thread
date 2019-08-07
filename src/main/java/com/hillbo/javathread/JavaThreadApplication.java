package com.hillbo.javathread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.hillbo.*"})
@SpringBootApplication
public class JavaThreadApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaThreadApplication.class, args);
	}

}
