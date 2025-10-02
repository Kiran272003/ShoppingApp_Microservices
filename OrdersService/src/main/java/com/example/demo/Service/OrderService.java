package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Exceptions.BadRequestException;
import com.example.demo.Exceptions.ResourceNotFoundException;
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
		if(order==null)
		{
			throw new BadRequestException("The Order can not be Empty");
			
		}
		return orderRepository.save(order);
	}
	
	public List<Order> getOrdersByCustomerId(Long CutomerId)
	{
	
		return orderRepository.getByCustomerId(CutomerId);
	}
	
	public void deleteOrder(Long id)
	{
		if(orderRepository.findById(id) == null)
		{
			throw new ResourceNotFoundException("There is no Order delete with the give ID:" + id);
		}
		orderRepository.deleteById(id);
	}
	
	public Optional<Order> getOrderBy(Long id)
	{
		if(orderRepository.findById(id) == null)
		{
			throw new ResourceNotFoundException("No Order Found with the given ID : " + id);
		}
		return orderRepository.findById(id);
	}
	
	
}
