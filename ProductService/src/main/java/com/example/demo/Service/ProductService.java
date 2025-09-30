package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repo.*;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

	
	@Autowired
	private ProductRepository productRepository;
	
	
	public List<Product> getAllProducts()
	{
		return productRepository.findAll();
	}
	
	public Product addProduct(Product product)
	{
		return productRepository.save(product);
	}
	
	public String deleteProduct(Long id)
	{
		productRepository.deleteById(id);
		return "Delete Successful";
	}
	
	public Product getById(Long Id)
	{
		return productRepository.findById(Id)
				.orElseThrow(()->new RuntimeException("Product not found with the id" + Id));
	}
	
	public Product updateProduct(Product product,Long pid)
	{
		Product pro = productRepository.findById(pid)
				.orElseThrow(()-> new  RuntimeException("Product not found with the given ID"));
		
		pro.setProductname(product.getProductname());
		pro.setQuantity(product.getQuantity());
		return pro;
	}
	
	public Integer getQtyById(Integer id)
	{
		return productRepository.getQtyById(id);
	}
	
	@Transactional
	public void updateQty(Integer qty,Long id)
	{
		productRepository.updateQuantity(qty, id);
	}
}
