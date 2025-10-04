package com.example.demo.Repo;

/**
 * Data Transfer Object (DTO) representing a product's
 * available quantity and unit price.
 * <p>
 * This class is used to transfer product stock and pricing
 * information between services (e.g., Product Service → Order Service)
 * without exposing the entire Product entity.
 * </p>
 */
public class ProductQtyPriceDTO {

    /** The available stock quantity of the product */
    private Integer quantity;

    /** The unit price of the product */
    private Integer price;

    /** Default constructor (required for serialization/deserialization) */
    public ProductQtyPriceDTO() {
    }

    /**
     * Parameterized constructor for easy object creation.
     *
     * @param quantity available stock quantity
     * @param price    unit price of the product
     */
    public ProductQtyPriceDTO(Integer quantity, Integer price) {
        this.price = price;
        this.quantity = quantity;
    }

    // ---------- Getters and Setters ----------

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
