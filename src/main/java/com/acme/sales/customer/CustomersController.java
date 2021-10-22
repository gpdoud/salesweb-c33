package com.acme.sales.customer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomersController {

	@Autowired
	private CustomerRepository custRepo;
	
	@GetMapping
	public Iterable<Customer> GetAll() {
		return custRepo.findAll();
	}
	
	@GetMapping("{id}")
	public Customer GetById(@PathVariable int id) {
		var customer = custRepo.findById(id);
		if(customer.isEmpty()) {
			return null;
		}
		return customer.get();
	}
}
