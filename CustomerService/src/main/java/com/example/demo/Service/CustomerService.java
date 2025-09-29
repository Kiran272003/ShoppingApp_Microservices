package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repo.*;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	
	public List<Customer> getallCustomers()
	{
		return customerRepository.findAll();
		
	}
	
	public Customer addCustomer( Customer customer)
	{
		return customerRepository.save(customer);
	}
	
	public Optional<Customer> getById(Long id)
	{
		return customerRepository.findById(id);
	}
	
	public void deleteById(Long id)
	{
		customerRepository.deleteById(id);
	
		
	}
}
