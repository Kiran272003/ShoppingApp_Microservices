package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	
	@GetMapping("/order")
	public String gegtOrder()
	{
		return "orders";
		
	}
}
