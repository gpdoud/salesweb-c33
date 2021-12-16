package com.acme.sales.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Optional<Customer> findByCodeAndName(String code, String name);
}
