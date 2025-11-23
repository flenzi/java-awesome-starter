package com.example.company.domain.product.repository;

import com.example.company.domain.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Product entity.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Find products by name containing the search term (case-insensitive).
     *
     * @param name the search term
     * @return list of matching products
     */
    List<Product> findByNameContainingIgnoreCase(String name);

    /**
     * Find products with stock quantity less than or equal to specified value.
     *
     * @param quantity the stock quantity threshold
     * @return list of products with low stock
     */
    List<Product> findByStockQuantityLessThanEqual(Integer quantity);
}
