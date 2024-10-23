package com.elemlime.jdbidemo;

import org.springframework.boot.SpringApplication;

public class TestJdbidemoApplication {

	public static void main(String[] args) {
		SpringApplication.from(JdbidemoApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
