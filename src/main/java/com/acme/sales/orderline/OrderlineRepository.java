package com.acme.sales.orderline;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderlineRepository extends JpaRepository<Orderline, Integer>{
	List<Orderline> findOrderlineByOrderId(int orderId);
}
