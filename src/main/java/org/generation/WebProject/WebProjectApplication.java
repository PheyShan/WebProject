package org.generation.WebProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Spring Boot Framework uses a lot of annotations.
//Perform Spring configuration

@SpringBootApplication
public class WebProjectApplication {

	//JVM will locate main method as the entry point when we build and run our application
	public static void main(String[] args) {
		SpringApplication.run(WebProjectApplication.class, args);
	}

}
