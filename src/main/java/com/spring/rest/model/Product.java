package com.spring.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Inventory_Table")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "unitPrice")
	private Integer unitPrice;
	@Column(name = "quantity")
	private Integer quantity;

	public Product() {
		super();
	}

	public Product(Long id, String name, Integer unitPrice, Integer quantity) {
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}
}
