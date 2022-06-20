package com.carrito.app;

import com.carrito.app.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarritoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CarritoApplication.class, args);
	}

	@Autowired
	private UserService userService;
	@Override
	public void run(String... args) throws Exception {
		System.out.println(userService.findByEmailDto("fedebuchet17@gmail.com").get().getId());
	}
}
