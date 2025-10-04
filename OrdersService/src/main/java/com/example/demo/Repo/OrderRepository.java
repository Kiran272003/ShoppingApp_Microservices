package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository interface for the {@link Order} entity.
 * <p>
 * This interface provides CRUD operations and 
 * query methods for interacting with the "Orders" table.
 * It extends {@link JpaRepository}, which offers built-in 
 * support for pagination, sorting, and custom queries.
 * </p>
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Retrieves all orders associated with a specific customer.
     *
     * @param customerId the ID of the customer whose orders should be fetched
     * @return a list of orders belonging to the given customer
     */
    List<Order> getByCustomerId(Long customerId);
}
