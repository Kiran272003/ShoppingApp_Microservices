package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.BadRequestException;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Repo.*;

import jakarta.transaction.Transactional;

/**
 * Service layer for managing product-related business logic.
 * <p>
 * This class handles all product operations including CRUD actions,
 * fetching product details, and updating product quantities. It acts
 * as a bridge between the controller layer and the repository layer.
 * </p>
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // -------------------------------------------------------------
    // Retrieve all products
    // -------------------------------------------------------------
    /**
     * Retrieves all products from the database.
     *
     * @return a list of all available products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // -------------------------------------------------------------
    // Add a new product
    // -------------------------------------------------------------
    /**
     * Adds a new product to the system.
     *
     * @param product the product to be added
     * @return the saved product entity
     * @throws BadRequestException if the product object is null
     */
    public Product addProduct(Product product) {
        if (product.getPrice()==0||product.getProductname()==null||product.getQuantity()==0) {
            throw new BadRequestException("Some product fields are empty");
        }
        return productRepository.save(product);
    }

    // -------------------------------------------------------------
    // Delete a product by ID
    // -------------------------------------------------------------
    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     * @return a confirmation message after successful deletion
     * @throws ResourceNotFoundException if the product does not exist
     */
    public String deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("No product found with the given ID: " + id);
        }
        productRepository.deleteById(id);
        return "Delete Successful";
    }

    // -------------------------------------------------------------
    // Get a product by ID
    // -------------------------------------------------------------
    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product
     * @return the found product entity
     * @throws ResourceNotFoundException if no product is found with the given ID
     */
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
    }

    // -------------------------------------------------------------
    // Update product details
    // -------------------------------------------------------------
    /**
     * Updates an existing product's details.
     *
     * @param product the product entity containing updated fields
     * @return the updated product entity
     * @throws ResourceNotFoundException if the product does not exist
     */
    public Product updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + product.getId()));
        
        // Save updated details
        return productRepository.save(product);
    }

    // -------------------------------------------------------------
    // Get quantity and price by product ID
    // -------------------------------------------------------------
    /**
     * Retrieves the quantity and price of a product by its ID.
     *
     * @param id the product ID
     * @return a DTO containing quantity and price
     */
    public ProductQtyPriceDTO getQtyById(Integer id) {
        return productRepository.getQtyById(id);
    }

    // -------------------------------------------------------------
    // Update product quantity
    // -------------------------------------------------------------
    /**
     * Updates the quantity of a product (used typically after an order).
     * <p>
     * This operation is transactional because it modifies the database.
     * </p>
     *
     * @param qty the amount to reduce or increase (use negative to add back)
     * @param id  the product ID
     */
    @Transactional
    public void updateQty(Integer qty, Long id) {
        productRepository.updateQuantity(qty, id);
    }
}
