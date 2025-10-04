package com.example.demo.Repo;

/**
 * Data Transfer Object (DTO) representing product stock and pricing details.
 * <p>
 * This class is used to share limited product data (quantity and price)
 * between microservices — for example, when the Order Service checks product
 * availability before placing an order.
 * </p>
 */
public class ProductQtyPriceDTO {

    /** The available stock quantity of the product */
    private Integer quantity;

    /** The unit price of the product */
    private Integer price;

    /**
     * Parameterized constructor for initializing product quantity and price.
     *
     * @param quantity available stock quantity
     * @param price    price per unit
     */
    public ProductQtyPriceDTO(Integer quantity, Integer price) {
        this.price = price;
        this.quantity = quantity;
    }

    // -------------------- Getters and Setters --------------------

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
