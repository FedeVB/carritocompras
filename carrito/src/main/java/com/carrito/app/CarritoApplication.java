package com.carrito.app;

import com.carrito.app.repository.CartRepository;
import com.carrito.app.domain.dto.ITotalDto;
import com.carrito.app.repository.UserRepository;
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
	private CartRepository cartRepository;
	@Autowired
	private UserRepository userRepository;
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Con la interface");
		ITotalDto totalInterface=cartRepository.getTotal(16L);
		System.out.println(totalInterface.getTotal());
		System.out.println(totalInterface.getCantidad());
		System.out.println("Ahora esto");
//		cartRepository.deleteById(22L);
//		cartRepository.deleteByIdCart(22L);
		System.out.println("·Dsasda");
		System.out.println(userRepository.findByUserEmail("fedebuchet17@gmail.com").get().getFirstName());
	}
}
