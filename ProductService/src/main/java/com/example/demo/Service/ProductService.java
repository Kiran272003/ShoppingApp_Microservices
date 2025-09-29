package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repo.*;

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
	
	public Product updateProduct(Product product,Long pid)
	{
		Product pro = productRepository.getById(pid);
		
		pro.setProductname(product.getProductname());
		pro.setQuantity(product.getQuantity());
		return pro;
	}
}
