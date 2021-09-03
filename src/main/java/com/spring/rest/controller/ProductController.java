package com.spring.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.spring.rest.model.Product;
import com.spring.rest.service.ProductService;

/**
 * Rest controller that publishes endpoints for list, get and persist products.
 */
@RestController
public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping("/product")
	public List<Product> getAllProducts() {
		try {
			return productService.getAllProducts();
		}
		catch (Exception exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product details Not Found", exc);
		}
	}

	@GetMapping("/product/{name}")
	public Product getProductDetails(@PathVariable String name) {
		try {
			return productService.getProductDetails(name);
		}
		catch (Exception exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product details Not Found for "+ name, exc);
		}
	}

	@PostMapping("/product")
	public HttpStatus addProduct(@RequestBody Product product) {
		productService.addProduct(product);
		return HttpStatus.CREATED;
	}
}

