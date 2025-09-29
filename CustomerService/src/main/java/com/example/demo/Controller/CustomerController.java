package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CustomerController {

	private final RestTemplate restTemplate;
	
	public CustomerController(RestTemplate restTemplate)
	{
		this.restTemplate=restTemplate;
	}
	
	@GetMapping("/customer")
	public String getCustomer()
	{
		return "hello";
	}
	
	
	@GetMapping("/getOrders")
	public String getOrders()
	{
		return restTemplate.getForObject("http://OrdersService/order", String.class);
		
	}
	
}
