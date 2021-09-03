package com.spring.rest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.spring.rest.model.Product;
import com.spring.rest.service.ProductService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTest {

	@MockBean
	ProductService productService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testAllProductsEndpoint() throws Exception {
		when(productService.getAllProducts()).thenReturn(Arrays.asList(new Product(1l,"Washing Machine",800,2 )));
		this.mockMvc.perform(get("/product")).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"));
	}

	@Test
	public void testProductDetailsEndpoint() throws Exception {
		when(productService.getProductDetails(Mockito.any())).thenReturn(new Product(1l,"Washing Machine",800,2 ));
		this.mockMvc.perform(get("/product/Washing Machine")).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"));
	}

	@Test
	public void testAddProductEndpoint() throws Exception {
		when(productService.addProduct(any())).thenReturn(true);
		String productJson = "{\"unitPrice\": 1000,\"name\" : \"TV\",\"quantity\": \"1\"}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/product")
				.accept(MediaType.APPLICATION_JSON).content(productJson)
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
		.andExpect(content().string("\"CREATED\""));
		
		}
}