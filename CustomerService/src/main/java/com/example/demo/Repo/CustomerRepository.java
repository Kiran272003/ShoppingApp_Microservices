package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Customer entity.
 * Provides CRUD operations and query methods via JpaRepository.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // No additional methods needed; JpaRepository provides basic CRUD operations
}
