package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repo.*;

@Service
public class OrderService {
	@Autowired
	private OrderRepository  orderRepository;
	
	public List<Order> getAllOrders()
	{
		return orderRepository.findAll();
	}
	
	public Order addOrder(Order order)
	{
		return orderRepository.save(order);
	}
	
	public List<Order> getOrdersByCustomerId(Long CutomerId)
	{
		return orderRepository.getByCustomerId(CutomerId);
	}
	
	
}
