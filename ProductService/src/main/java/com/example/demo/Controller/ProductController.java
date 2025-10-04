package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Repo.*;
import com.example.demo.Service.ProductService;

import java.util.*;

/**
 * REST controller that exposes endpoints for managing products.
 * <p>
 * This controller handles product-related operations such as
 * creating, reading, updating, deleting products,
 * and managing their quantity and pricing details.
 * </p>
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    // ------------------------------------------------------ Get Products -----------------

    /**
     * Retrieves all available products.
     *
     * @return a list of all products
     */
    @GetMapping("/product")
    public List<Product> getProduct() {
        return productService.getAllProducts();
    }

    // ------------------------------------------------------- Add Products ---------------

    /**
     * Adds a new product to the inventory.
     *
     * @param product the product to be added
     * @return the saved product
     */
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // ------------------------------------------------------- Delete Product ------------

    /**
     * Deletes a product by its ID.
     *
     * @param pid the ID of the product to delete
     * @return a success message
     */
    @DeleteMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("pid") Long pid) {
        productService.deleteProduct(pid);
        return "Product deleted successfully";
    }

    // ------------------------------------------------------- Get Product By ID ----------

    /**
     * Retrieves a single product by its ID.
     *
     * @param id the product ID
     * @return the product if found
     */
    @GetMapping("/getById")
    public Product getById(@RequestParam("id") Long id) {
        return productService.getById(id);
    }

    // --------------------------------------------------------- Update Product -----------

    /**
     * Updates product details such as name, price, or stock.
     *
     * @param product the updated product details
     * @return the updated product entity
     */
    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    // -------------------------------------------------------- Get Quantity By ID --------

    /**
     * Retrieves product quantity and price based on product ID.
     * Used primarily by the Order Service to validate stock before placing an order.
     *
     * @param id the product ID
     * @return a DTO containing quantity and price
     */
    @GetMapping("/getQtyById")
    public ProductQtyPriceDTO getQtyById(@RequestParam("id") Integer id) {
        return productService.getQtyById(id);
    }

    // -------------------------------------------------------- Update Quantity -----------

    /**
     * Updates the available quantity of a product (increase or decrease).
     * <p>
     * This endpoint is typically called when an order is placed or canceled.
     * </p>
     *
     * @param value the quantity to adjust (positive or negative)
     * @param id    the product ID
     */
    @PostMapping("/updateQty")
    public void updateQty(@RequestParam("qty") Integer value, @RequestParam("id") Long id) {
        productService.updateQty(value, id);
    }
}
