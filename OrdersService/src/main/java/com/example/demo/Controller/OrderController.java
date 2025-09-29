package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.OrderService;

import jakarta.ws.rs.GET;

import com.example.demo.Repo.*;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	@GetMapping("/stringOrder")
	public String stringOrder()
	{
		return "String type Orders";
	}
	@GetMapping("/order")
	public List<Order> getOrder()
	{
		return orderService.getAllOrders();
	}
	
	@PostMapping("/addOrder")
	public Order addOrder(@RequestBody Order order)
	{
		return orderService.addOrder(order);
	}
	
	@GetMapping("/getByCustomerId")
	public List<Order> getByCustomerId(@RequestParam("custmId") Long custmId)
	{
		return orderService.getOrdersByCustomerId(custmId);
	}
}
