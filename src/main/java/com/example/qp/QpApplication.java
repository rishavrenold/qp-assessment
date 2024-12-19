package com.example.qp;

import com.example.qp.Entity.GroceryItem;
import com.example.qp.repository.GroceryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QpApplication {

	public static void main(String[] args) {
		SpringApplication.run(QpApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(GroceryRepository repository) {
		return args -> {
			repository.save(new GroceryItem(1L,"Apple", 3.0, 100));
			repository.save(new GroceryItem(2L,"Milk", 1.5, 50));
		};
	}
}
