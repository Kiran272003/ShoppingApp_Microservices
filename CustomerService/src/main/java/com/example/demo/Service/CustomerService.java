package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.BadRequestException;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Repo.*;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> getallCustomers() {
		return customerRepository.findAll();

	}

	public Customer addCustomer(Customer customer) {
		if (customer == null) {
			throw new BadRequestException("The Customer can not be Empty");
		}
		return customerRepository.save(customer);
	}

	public Optional<Customer> getById(Long id) {
		if (customerRepository.findById(id) == null) {
			throw new ResourceNotFoundException("There is no Customer with the given ID : " + id);
		}
		return customerRepository.findById(id);
	}

	public void deleteById(Long id) {
		if (customerRepository.findById(id) == null) {
			throw new ResourceNotFoundException("There is no Customer delete with the given ID : " + id);
		}
		customerRepository.deleteById(id);

	}
	
	public Customer updateCustomer(Customer customer)
	{
		if(customer.getId()==null)
		{
			throw new ResourceNotFoundException("The Customer not found with the given ID" + customer.getId());
		}
		return customerRepository.save(customer);
	}
}
