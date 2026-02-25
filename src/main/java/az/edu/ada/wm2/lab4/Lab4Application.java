package az.edu.ada.wm2.lab4;

import az.edu.ada.wm2.lab4.repository.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab4Application {

	@Autowired
	private ProductRepositoryImpl productRepository;

	public static void main(String[] args) {

		SpringApplication.run(Lab4Application.class, args);
	}
}
