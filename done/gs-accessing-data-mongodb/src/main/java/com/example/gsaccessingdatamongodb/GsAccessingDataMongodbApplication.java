package com.example.gsaccessingdatamongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GsAccessingDataMongodbApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(GsAccessingDataMongodbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();

		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));

		System.out.println("Customers found with findAll:");
		System.out.println("-----------------------------");
		repository.findAll().forEach(System.out::println);
		System.out.println();

		System.out.println("Customer found with findByFirstName:");
		System.out.println("-----------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName:");
		System.out.println("-----------------------------");
		repository.findByLastName("Smith").forEach(System.out::println);
		System.out.println();

	}
}
