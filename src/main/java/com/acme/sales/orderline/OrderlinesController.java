package com.acme.sales.orderline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/orderlines")
public class OrderlinesController {
	
	@Autowired
	private OrderlineRepository ordlineRepo;

	@GetMapping
	public Iterable<Orderline> GetAll() {
		return ordlineRepo.findAll();
	}
}
