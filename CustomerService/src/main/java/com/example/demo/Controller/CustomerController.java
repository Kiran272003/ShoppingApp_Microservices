package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.example.demo.Repo.*;
import com.example.demo.Service.CustomerService;
import java.util.Optional;

@RestController
public class CustomerController {

	
	@Autowired
	private CustomerService customerService;
	
	private RestTemplate restTemplate;
	
	public CustomerController(RestTemplate restTemplate)
	{
		this.restTemplate=restTemplate;
	}
	
//	-----------------------------------------------------------Get Customer -----------------------------------
	@GetMapping("/customer")
	public List<Customer> getCustomer()
	{
		return customerService.getallCustomers();
	}
	
//------------------------------------------------------- Add Customer-----------------------------------
	@PostMapping("/addCustomer")
	public Customer addCustomer(@RequestBody Customer customer)
	{
		return customerService.addCustomer(customer);
	}
	
	@GetMapping("/getMyOrders")
	public String getMyOrders(@RequestParam("customerId") Long customerId)
	{
		String URL = "http://OrdersService/getByCustomerId?custmId={id}";
		return restTemplate.getForObject(URL, String.class,customerId);
	}
	
//	----------------------------------- Get products------------------------
	@GetMapping("/getProducts")
	public String getProducts()
	{
		return restTemplate.getForObject("http://ProductService/product", String.class);
	}
	
//---------------------------------------Place Order ----------------------
	@PostMapping("/placeOrder")
	public String placeOrder(@RequestBody Object order)
	{
		 restTemplate.postForObject("http://OrdersService/addOrder", order, String.class);
		 return "Order Placed Succefully";
	}
	
//-------------------------------------- Delete Order ---------------------
	
	@DeleteMapping("/deleteOrder")
	public String deleteOrder(@RequestParam("id") Long id)
	{
		 restTemplate.delete("http://OrdersService/deleteOrder?id={id}",id);
		 return "Order deleted successful";
	}
//	----------------------------------------------------- Delete by Id--------------------------
	@DeleteMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("deleteId") Long deleteId)
	{
		customerService.deleteById(deleteId);
		return "Customer deleted successfully";
	}
	
	
// -------------------------------------------------------Customer By Id-----------------------
	@GetMapping("/getById")
	public Optional<Customer> getCustomerById(@RequestParam("id") Long id)
	{
		return customerService.getById(id);
	}
	
//------------------------------------------------------- Update Customer ---------------------
	
	@PutMapping("/updateCustomer")
	public Customer updateCustomer(@RequestBody Customer customer)
	{
		return customerService.updateCustomer(customer);
	}
	
}
