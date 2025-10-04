package com.example.demo.Repo;

import jakarta.persistence.*;

/**
 * Entity class representing a Product in the system.
 * <p>
 * This class maps to the "Product" table in the database and 
 * stores product-related information such as name, price, and available quantity.
 * </p>
 */
@Entity
@Table(name = "Product")
public class Product {

    /** Unique identifier for each product (Primary Key) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** Name of the product */
    @Column(name = "ProductName")
    private String productname;

    /** Available stock quantity of the product */
    @Column(name = "Quantity")
    private Integer quantity;

    /** Price per unit of the product */
    @Column(name = "Price")
    private int price;

    /** Default constructor (required by JPA) */
    public Product() {
    }

    // -------------------- Getters and Setters --------------------

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
