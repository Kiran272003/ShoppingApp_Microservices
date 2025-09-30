package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Service.OrderService;

import jakarta.ws.rs.GET;

import com.example.demo.Repo.*;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private RestTemplate restTemplate;
//	@GetMapping("/stringOrder")
//	public String stringOrder()
//	{
//		return "String type Orders";
//	}
	
// ------------------------------------------------------ Get Orders ------------------
	@GetMapping("/order")
	public List<Order> getOrder()
	{
		return orderService.getAllOrders();
	}

//---------------------------------------------------- Add Order --------------------------
	@PostMapping("/addOrder")
	public String addOrder(@RequestBody Order order)
	{
		Integer pQty = restTemplate.getForObject("http://ProductService/getQtyById?id={id}", Integer.class,order.getProductId());
		if(order.getQuantity()<=pQty)
		{
			restTemplate.postForObject("http://ProductService/updateQty?qty={qty}&id={id}",null, Integer.class, order.getQuantity(),order.getProductId());
			orderService.addOrder(order);
		}
		else {
			throw new RuntimeException("No enough Stock");
		}
		return "Order placed successfully";
		
	}
// --------------------------------------------------- Get Order By Customer ID -----------	
	@GetMapping("/getByCustomerId")
	public List<Order> getByCustomerId(@RequestParam("custmId") Long custmId)
	{
		return orderService.getOrdersByCustomerId(custmId);
	}
	
	@GetMapping("/getOrderBy")
	public Order getOrderBy(@RequestParam("id") Long id)
	{
		return orderService.getOrderBy(id)
				.orElseThrow(()->new RuntimeException("No Order with the given ID"));
	}
	
	@DeleteMapping("/deleteOrder")
	public String deleteOrder(@RequestParam("id") Long id)
	{
		Order order = orderService.getOrderBy(id)
				.orElseThrow(()->new RuntimeException("No order with given Id"));
		restTemplate.postForObject("http://ProductService/updateQty?qty={qty}&id={id}",null, Integer.class,-order.getQuantity(),order.getProductId());
		
		orderService.deleteOrder(id);
		
		return "Delete successful";
	}
}
