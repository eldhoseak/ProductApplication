package com.spring.rest.service;

import java.util.List;

import javax.transaction.Transactional;

import com.spring.rest.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest.model.Product;
import com.spring.rest.repository.ProductRepository;

/**
 * The service class that fetches details from repo.
 */
@Service
public class ProductService {

	@Autowired
	ProductRepository<Product> productRepository;

	public List<Product> getAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	public Product getProductDetails(String name) throws ResourceNotFoundException{
		List<Product> products= productRepository.findByNameIgnoreCase(name);
		if(products.isEmpty()) throw new ResourceNotFoundException("Product not found");
		return products.get(0);
	}

	@Transactional
	public boolean addProduct(Product product) {
		return productRepository.save(product) != null;
	}

	}
