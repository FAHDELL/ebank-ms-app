package com.fahd.customer_service;

import com.fahd.customer_service.entities.Customer;
import com.fahd.customer_service.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(CustomerService customerService){
		return args -> {
			List<String> names = List.of("Mohammed", "Imane" , "Yassine");

			names.forEach(name ->{
				customerService.saveCustomer(Customer.builder().name(name).email(name +"@gmail.com").build());
			});
		};
	}

}
