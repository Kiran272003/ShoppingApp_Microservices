package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Repo.Customer;
import com.example.demo.Service.CustomerService;

@RestController // Marks this class as a REST controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private RestTemplate restTemplate;

    // Constructor injection for RestTemplate
    public CustomerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // ------------------------- Get all customers -------------------------
    @GetMapping("/customer")
    public List<Customer> getCustomer() {
        return customerService.getallCustomers();
    }

    // ------------------------- Add a new customer -----------------------
    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    // ------------------------- Get orders of a specific customer ----------------
    @GetMapping("/getMyOrders")
    public String getMyOrders(@RequestParam("customerId") Long customerId) {
        String URL = "http://OrdersService/getByCustomerId?custmId={id}";
        return restTemplate.getForObject(URL, String.class, customerId);
    }

    // ------------------------- Get all products -------------------------
    @GetMapping("/getProducts")
    public String getProducts() {
        return restTemplate.getForObject("http://ProductService/product", String.class);
    }

    // ------------------------- Place a new order ------------------------
    @PostMapping("/placeOrder")
    public String placeOrder(@RequestBody Object order) {
        restTemplate.postForObject("http://OrdersService/addOrder", order, String.class);
        return "Order Placed Successfully";
    }

    // ------------------------- Delete an order --------------------------
    @DeleteMapping("/deleteOrder")
    public String deleteOrder(@RequestParam("id") Long id) {
        restTemplate.delete("http://OrdersService/deleteOrder?id={id}", id);
        return "Order deleted successfully";
    }

    // ------------------------- Delete a customer ------------------------
    @DeleteMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("deleteId") Long deleteId) {
        customerService.deleteById(deleteId);
        return "Customer deleted successfully";
    }

    // ------------------------- Get customer by ID ------------------------
    @GetMapping("/getById")
    public Optional<Customer> getCustomerById(@RequestParam("id") Long id) {
        return customerService.getById(id);
    }

    // ------------------------- Update customer ---------------------------
    @PutMapping("/updateCustomer")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }
}
