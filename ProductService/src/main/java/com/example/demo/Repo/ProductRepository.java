package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("Select quantity from Product p where p.id =:id")
	public Integer getQtyById(@Param("id") Integer id);
	@Modifying
	@Query("Update Product p set p.quantity=p.quantity-:value where p.id=:id")
	public  void updateQuantity(@Param("value") Integer value,@Param("id") Long id);
}
