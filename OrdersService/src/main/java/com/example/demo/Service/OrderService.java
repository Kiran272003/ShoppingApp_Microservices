package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.BadRequestException;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Repo.*;

/**
 * Service class that handles business logic related to Orders.
 * <p>
 * This class acts as a bridge between the controller layer and the repository layer.
 * It performs validations and ensures proper error handling before interacting with the database.
 * </p>
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Fetches all orders from the database.
     *
     * @return list of all orders
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Adds a new order to the database after validation.
     *
     * @param order the order object to be added
     * @return the saved order
     * @throws BadRequestException if the order object is null
     */
    public Order addOrder(Order order) {
        if (order.getCustomerId() == null||order.getCustomerName()==null||order.getProductId()==null||order.getQuantity()==0) {
            throw new BadRequestException("Some Order fields are empty");
        }
        return orderRepository.save(order);
    }

    /**
     * Retrieves all orders belonging to a specific customer.
     *
     * @param customerId the ID of the customer
     * @return list of orders placed by that customer
     */
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.getByCustomerId(customerId);
    }

    /**
     * Deletes an order by its ID after verifying its existence.
     *
     * @param id the ID of the order to delete
     * @throws ResourceNotFoundException if the order does not exist
     */
    public void deleteOrder(Long id) {
        if (orderRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("No Order found with the given ID: " + id);
        }
        orderRepository.deleteById(id);
    }

    /**
     * Retrieves a single order by its ID.
     *
     * @param id the ID of the order to retrieve
     * @return an Optional containing the order if found
     * @throws ResourceNotFoundException if the order does not exist
     */
    public Optional<Order> getOrderBy(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new ResourceNotFoundException("No Order found with the given ID: " + id);
        }
        return order;
    }
}
