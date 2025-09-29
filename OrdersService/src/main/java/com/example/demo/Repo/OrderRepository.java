package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

	
	public List<Order> getByCustomerId(Long customerId);
}
