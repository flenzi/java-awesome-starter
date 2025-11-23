package com.example.company.domain.product.service;

import com.example.company.common.exception.ResourceNotFoundException;
import com.example.company.domain.product.model.Product;
import com.example.company.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer for Product domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    /**
     * Retrieve all products.
     *
     * @return list of all products
     */
    public List<Product> getAllProducts() {
        logger.debug("Fetching all products");
        return productRepository.findAll();
    }

    /**
     * Retrieve a product by ID.
     *
     * @param id the product ID
     * @return the product
     * @throws ResourceNotFoundException if product not found
     */
    public Product getProductById(Long id) {
        logger.debug("Fetching product with id: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }

    /**
     * Search products by name.
     *
     * @param name the search term
     * @return list of matching products
     */
    public List<Product> searchProductsByName(String name) {
        logger.debug("Searching products with name containing: {}", name);
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Create a new product.
     *
     * @param product the product to create
     * @return the created product
     */
    @Transactional
    public Product createProduct(Product product) {
        logger.info("Creating new product: {}", product.getName());
        return productRepository.save(product);
    }

    /**
     * Update an existing product.
     *
     * @param id      the product ID
     * @param product the updated product data
     * @return the updated product
     * @throws ResourceNotFoundException if product not found
     */
    @Transactional
    public Product updateProduct(Long id, Product product) {
        logger.info("Updating product with id: {}", id);
        Product existingProduct = getProductById(id);
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStockQuantity(product.getStockQuantity());
        return productRepository.save(existingProduct);
    }

    /**
     * Delete a product by ID.
     *
     * @param id the product ID
     * @throws ResourceNotFoundException if product not found
     */
    @Transactional
    public void deleteProduct(Long id) {
        logger.info("Deleting product with id: {}", id);
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}
