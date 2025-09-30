package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
	
	public void deleteOrder(Long id)
	{
		orderRepository.deleteById(id);
	}
	
	public Optional<Order> getOrderBy(Long id)
	{
		return orderRepository.findById(id);
	}
	
	
}
