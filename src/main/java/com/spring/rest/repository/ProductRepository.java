package com.spring.rest.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.rest.model.Product;

/**
 * CRUD repository for products.
 * @param <P>
 */
public interface ProductRepository<P> extends CrudRepository<Product, Long> {
    List<Product> findByNameIgnoreCase(String name);
}