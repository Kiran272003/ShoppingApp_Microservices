package com.example.demo.Repo;

public class ProductQtyPriceDTO {

	
	private Integer quantity;
	
	private Integer price;
	
	public ProductQtyPriceDTO()
	{
		
	}
	
	public ProductQtyPriceDTO(Integer quantity,Integer price)
	{
		this.price=price;
		this.quantity=quantity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}
