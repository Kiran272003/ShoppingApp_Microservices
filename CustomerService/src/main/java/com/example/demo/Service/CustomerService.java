package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.BadRequestException;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Repo.*;

@Service // Marks this class as a Spring service component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // ------------------------- Get all customers ------------------------
    public List<Customer> getallCustomers() {
        return customerRepository.findAll();
    }

    // ------------------------- Add a new customer ----------------------
    public Customer addCustomer(Customer customer) {
        if (customer.getName() == null || customer.getAddress()==null||customer.getCity()==null || customer.getEmail()==null||customer.getState()==null) {
            throw new BadRequestException("Some fields are empty");
        }
        return customerRepository.save(customer);
    }

    // ------------------------- Get customer by ID ----------------------
    public Optional<Customer> getById(Long id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isEmpty()) {
            throw new ResourceNotFoundException("No Customer found with the given ID: " + id);
        }
        return customerOpt;
    }

    // ------------------------- Delete customer by ID -------------------
    public void deleteById(Long id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isEmpty()) {
            throw new ResourceNotFoundException("No Customer found to delete with the given ID: " + id);
        }
        customerRepository.deleteById(id);
    }

    // ------------------------- Update existing customer ----------------
    public Customer updateCustomer(Customer customer) {
        if (customer.getId() == null) {
            throw new ResourceNotFoundException(
                    "Cannot update Customer. ID not provided: " + customer.getId());
        }
        return customerRepository.save(customer);
    }
}
