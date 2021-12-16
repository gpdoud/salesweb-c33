package com.acme.sales.customer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomersController {

	@Autowired
	private CustomerRepository custRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Customer>> GetAll() {
		var customers = custRepo.findAll();
		return new ResponseEntity<Iterable<Customer>>(customers, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Customer> GetById(@PathVariable int id) {
		var customer = custRepo.findById(id);
		if(customer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Customer> Insert(@RequestBody Customer customer) {
		if(customer == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var newCustomer = custRepo.save(customer);
		return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity Update(@PathVariable int id, @RequestBody Customer customer) {
		if(customer.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var oldCustomer = custRepo.findById(customer.getId());
		if(oldCustomer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		custRepo.save(customer);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity Delete(@PathVariable int id) {
		var customer = custRepo.findById(id);
		if(customer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		custRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("{code}/{name}")
	public ResponseEntity<Customer> GetByCodeAndName(@PathVariable String code, @PathVariable String name) {
		var customer = custRepo.findByCodeAndName(code, name);
		if(customer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
	}
}
