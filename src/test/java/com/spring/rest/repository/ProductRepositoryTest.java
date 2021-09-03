package com.spring.rest.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.rest.model.Product;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {


    @Autowired
    private ProductRepository<Product> productRepository;

    @Test
    public void testfindByName(){
        productRepository.save(new Product(1l,"Washing Machine",800,2 ));
    Product product = (Product) productRepository.findByNameIgnoreCase("Washing Machine").get(0);

    assertEquals("Washing Machine", product.getName());

        productRepository.deleteAll();
    Assertions.assertThat(productRepository.findAll()).isEmpty();
}
}