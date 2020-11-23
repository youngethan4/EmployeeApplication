package com.tcs.springbootemployee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootemployeeApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringbootemployeeApplication.class, args);
		
		Menu.start(context);
	}

}
