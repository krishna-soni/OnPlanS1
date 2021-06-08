package com.onplans1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {
	"com.onplans1.filter",
	"com.onplans1.handler",
	"com.onplans1.interceptor",
	"com.onplans1.router",
	"com.onplans1.metrics"
})

public class Onplans1Application {

	public static void main(String[] args) {
		SpringApplication.run(Onplans1Application.class, args);
	}

}
