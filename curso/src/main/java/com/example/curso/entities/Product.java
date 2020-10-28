package com.example.curso.entities;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "tb_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	private String Name;
	private String description;
	private Double price;
	private String imgUrl;

	// o mesmo produto nao pode ter mais de uma categoria 
	// colecao nao comece nula, mais vazia				
	@ManyToMany																
	@JoinTable(name = "tb_product_category",
	joinColumns = @JoinColumn(name = "product_id"),//chave estrangeira do produto
	inverseJoinColumns = @JoinColumn(name="category_id"))
	private Set<Category> categories = new HashSet<>();//nome da colecao
	// Set e uma interface nao pode ser instanciado por isso usa HashSet, nao precisa colocar no contrutor

	@OneToMany(mappedBy  = "id.product")
	private Set<OrderItem> items  = new HashSet<>();
	
	public Product() {

	}
	//pq nao colocou colecao dentro construtor = ja sendo instanciada la em cima
	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		Name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
	}
	
	@JsonIgnore
	public 	Set<Order> getOrders(){
		Set<Order> set  = new HashSet<>();
		for(OrderItem x : items) {
			set.add(x.getOrder());
			
		}
		return set;
		
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
