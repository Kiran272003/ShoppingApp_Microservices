package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.BadRequestException;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Repo.*;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

	
	@Autowired
	private ProductRepository productRepository;
	
//-------------------------------------------------- Get All Products ---------------	
	public List<Product> getAllProducts()
	{
		return productRepository.findAll();
	}
	
	public Product addProduct(Product product)
	{
		if(product==null)
		{
			throw new BadRequestException("The Product can not be empty");
		}
		return productRepository.save(product);
	}
	
	public String deleteProduct(Long id)
	{
		if(productRepository.findById(id) == null )
		{
			throw new ResourceNotFoundException("There is no Product with the given Id" + id);
		}
		productRepository.deleteById(id);
		return "Delete Successful";
	}
	
	public Product getById(Long Id)
	{
		return productRepository.findById(Id)
				.orElseThrow(()->new ResourceNotFoundException("Product not found with the id" + Id));
	}
	
	public Product updateProduct(Product product)
	{
		Product pro = productRepository.findById(product.getId())
				.orElseThrow(()-> new  ResourceNotFoundException("Product not found with the given ID" + product.getId()));
		productRepository.save(product);
		return pro;
	}
	
	public ProductQtyPriceDTO getQtyById(Integer id)
	{
		return productRepository.getQtyById(id);
	}
	
	@Transactional
	public void updateQty(Integer qty,Long id)
	{
		productRepository.updateQuantity(qty, id);
	}
}
