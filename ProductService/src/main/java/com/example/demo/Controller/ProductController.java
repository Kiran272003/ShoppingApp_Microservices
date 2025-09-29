package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Repo.*;
import com.example.demo.Service.ProductService;

import java.util.*;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/product")
	public List<Product> getProduct()
	{
		return productService.getAllProducts();
	}
	
	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product)
	{
		return productService.addProduct(product);
	}
	
	@DeleteMapping("/deleteProduct")
	public String deleteProduct(@RequestParam("pid") Long pid)
	{
		 productService.deleteProduct(pid);
		 return "Delete Succesful";
		 
	}
}
