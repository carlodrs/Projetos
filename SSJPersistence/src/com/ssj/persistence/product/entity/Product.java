package com.ssj.persistence.product.entity;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Long;
import java.lang.String;
import java.util.Set;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity

public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String shortName;
	private String longName;
	private String description;
	private Double price;
	private Double offerPrice;
	private Double percentDiscount;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	private Set<Attribute> attributes;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Category category;
	
	private static final long serialVersionUID = 1L;

	public Product() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}   
	public String getLongName() {
		return this.longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}   
	public Double getOfferPrice() {
		return this.offerPrice;
	}

	public void setOfferPrice(Double offerPrice) {
		this.offerPrice = offerPrice;
	}   
	public Double getPercentDiscount() {
		return this.percentDiscount;
	}

	public void setPercentDiscount(Double percentDiscount) {
		this.percentDiscount = percentDiscount;
	}   
	public Set<Attribute> getAttributes() {
		return this.attributes;
	}

	public void setAttributeList(Set<Attribute> attributeList) {
		this.attributes = attributeList;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
   
	
}