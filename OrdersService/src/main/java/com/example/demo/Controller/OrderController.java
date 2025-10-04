package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Service.OrderService;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Repo.*;

/**
 * OrderController handles all HTTP requests related to Order operations.
 * It interacts with the OrderService for business logic and communicates 
 * with the ProductService via RestTemplate for stock and pricing updates.
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    // ------------------------------------------------------ Get All Orders ----------------------
    /**
     * Fetches and returns a list of all existing orders.
     * 
     * @return List of Order objects
     */
    @GetMapping("/order")
    public List<Order> getOrder() {
        return orderService.getAllOrders();
    }

    // ------------------------------------------------------ Add New Order -----------------------
    /**
     * Places a new order if the requested product quantity is available.
     * It calls ProductService to verify stock and update the remaining quantity.
     * 
     * @param order Order object with customer ID, product ID, and quantity
     * @return Success message if order is placed successfully
     */
    @PostMapping("/addOrder")
    public String addOrder(@RequestBody Order order) {
        // Fetch product quantity and price from ProductService
        ProductQtyPriceDTO obj = restTemplate.getForObject(
                "http://ProductService/getQtyById?id={id}",
                ProductQtyPriceDTO.class,
                order.getProductId()
        );

        // Check stock availability before placing order
        if (order.getQuantity() <= obj.getQuantity()) {

            // Reduce product quantity in ProductService
            restTemplate.postForObject(
                    "http://ProductService/updateQty?qty={qty}&id={id}",
                    null,
                    Integer.class,
                    order.getQuantity(),
                    order.getProductId()
            );

            // Calculate and set total order price
            order.setOrderPrice(order.getQuantity() * obj.getPrice());

            // Save order details
            orderService.addOrder(order);
        } else {
            throw new ResourceNotFoundException("Not enough stock available");
        }

        return "Order placed successfully";
    }

    // -------------------------------------------------- Get Orders by Customer ID ----------------
    /**
     * Retrieves all orders placed by a specific customer.
     * 
     * @param custmId ID of the customer
     * @return List of orders belonging to the customer
     */
    @GetMapping("/getByCustomerId")
    public List<Order> getByCustomerId(@RequestParam("custmId") Long custmId) {
        return orderService.getOrdersByCustomerId(custmId);
    }

    // -------------------------------------------------- Get Order by ID ---------------------------
    /**
     * Retrieves a single order by its unique order ID.
     * 
     * @param id Order ID
     * @return Order object if found
     */
    @GetMapping("/getOrderBy")
    public Order getOrderBy(@RequestParam("id") Long id) {
        return orderService.getOrderBy(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Order found with ID: " + id));
    }

    // -------------------------------------------------- Delete Order ------------------------------
    /**
     * Deletes an order and restores the product quantity back to ProductService.
     * 
     * @param id Order ID
     * @return Success message if deletion is completed
     */
    @DeleteMapping("/deleteOrder")
    public String deleteOrder(@RequestParam("id") Long id) {
        // Find existing order
        Order order = orderService.getOrderBy(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Order found with ID: " + id));

        // Restore product quantity when order is cancelled
        restTemplate.postForObject(
                "http://ProductService/updateQty?qty={qty}&id={id}",
                null,
                Integer.class,
                -order.getQuantity(),
                order.getProductId()
        );

        // Delete order record
        orderService.deleteOrder(id);

        return "Order deleted successfully";
    }
}
