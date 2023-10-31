package com.principal.todoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ToDoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoServiceApplication.class, args);
	}

}
