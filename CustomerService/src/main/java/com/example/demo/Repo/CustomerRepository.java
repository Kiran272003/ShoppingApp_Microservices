package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  CustomerRepository extends JpaRepository<Customer, Long> {

}
