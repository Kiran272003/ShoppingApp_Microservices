package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for managing {@link Product} entities.
 * <p>
 * Extends {@link JpaRepository} to provide CRUD operations and also defines
 * custom queries for retrieving product quantity and price, as well as for
 * updating stock quantities.
 * </p>
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Retrieves the quantity and price of a product by its ID.
     * <p>
     * Uses a JPQL query to return a {@link ProductQtyPriceDTO} object that
     * contains only the required fields (quantity and price), improving efficiency
     * by avoiding retrieval of unnecessary columns.
     * </p>
     *
     * @param id the ID of the product
     * @return a DTO containing the quantity and price of the product
     */
    @Query("SELECT new com.example.demo.Repo.ProductQtyPriceDTO(p.quantity, p.price) FROM Product p WHERE p.id = :id")
    ProductQtyPriceDTO getQtyById(@Param("id") Integer id);

    /**
     * Decreases the quantity of a product by a specified value.
     * <p>
     * This query performs an in-place update to reduce the product’s available stock.
     * It is annotated with {@link Modifying} because it alters the database state.
     * </p>
     *
     * @param value the quantity to subtract
     * @param id    the ID of the product to update
     */
    @Modifying
    @Query("UPDATE Product p SET p.quantity = p.quantity - :value WHERE p.id = :id")
    void updateQuantity(@Param("value") Integer value, @Param("id") Long id);
}
