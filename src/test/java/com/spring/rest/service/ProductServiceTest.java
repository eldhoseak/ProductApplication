package com.spring.rest.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.rest.model.Product;
import com.spring.rest.repository.ProductRepository;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

    @TestConfiguration
    static class ProductServiceImplTestContextConfiguration {

        @Bean
        public ProductService productService() {
            return new ProductService();
        }
    }

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testAllProducts(){

        when(productRepository.findAll()).thenReturn(Arrays.asList(new Product(1l,"Washing Machine",800,2 )));

        List<Product> products =  productService.getAllProducts();
        assertEquals("Washing Machine", products.get(0).getName());
    }

    @Test
    public void testProductDetails(){
       try {
           when(productRepository.findByNameIgnoreCase(Mockito.any())).thenReturn(Arrays.asList(new Product(1l, "Washing Machine", 800, 2)));

           Product product = productService.getProductDetails("Washing Machine");
           assertEquals("Washing Machine", product.getName());
       }catch(Exception e){
           e.printStackTrace();
       }
    }
}