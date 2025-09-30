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
	
//------------------------------------------------------ Get Products -----------------	
	@GetMapping("/product")
	public List<Product> getProduct()
	{
		return productService.getAllProducts();
	}
	
// ------------------------------------------------------- Add Products ---------------
	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product)
	{
		return productService.addProduct(product);
	}

//	-------------------------------------------------------Delete Product ------------
	@DeleteMapping("/deleteProduct")
	public String deleteProduct(@RequestParam("pid") Long pid)
	{
		 productService.deleteProduct(pid);
		 return "Delete Succesful";
		 
	}
	
//------------------------------------------------------- Get By ID------------------
	
	@GetMapping("/getById")
	public Product getById(@RequestParam("id") Long id)
	{
		 return productService.getById(id);
	}
	
//---------------------------------------------------------Update Product-------------
	@PostMapping("/updateProduct")
	public Product updateProduct(@RequestBody Product pro,@RequestParam("id") Long id)
	{
		return productService.updateProduct(pro, id);
		
		 
	}
	
	
//--------------------------------------------------------Get qty by ID-------------
	@GetMapping("/getQtyById")
	public Integer getQtyById(@RequestParam("id") Integer id)
	{
		return productService.getQtyById(id);
	}
	
	
// --------------------------------------------------------Update the quantity -----------
	
	@PostMapping("/updateQty")
	public void updateQty(@RequestParam("qty") Integer value,@RequestParam("id") Long id)
	{
		productService.updateQty(value, id);
	}
}
