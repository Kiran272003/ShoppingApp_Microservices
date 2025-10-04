package com.example.demo.Repo;

import jakarta.persistence.*;

/**
 * The {@code Order} entity represents an order placed by a customer
 * in the system. It stores details such as the customer name, 
 * product ID, quantity, and total order price.
 * 
 * <p>This entity is mapped to the "Orders" table in the database.</p>
 */
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "CustomerName", nullable = false)
    private String customerName;

    @Column(name = "ProductId", nullable = false)
    private Long productId;

    @Column(name = "Quantity", nullable = false)
    private int quantity;

    @Column(name = "CustomerId", nullable = false)
    private Long customerId;

    @Column(name = "Price")
    private Integer orderPrice;

    // ------------------------------------------------ Constructors ------------------------------------------------
    /**
     * Default constructor for JPA.
     */
    public Order() {
    }

    // ------------------------------------------------ Getters and Setters -----------------------------------------
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    // ------------------------------------------------ toString() ---------------------------------------------------
    /**
     * Returns a string representation of the order, useful for debugging and logging.
     */
    @Override
    public String toString() {
        return "Order{" +
                "Id=" + Id +
                ", customerName='" + customerName + '\'' +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", customerId=" + customerId +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
