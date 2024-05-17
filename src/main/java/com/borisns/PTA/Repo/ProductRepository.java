package com.borisns.PTA.Repo;

import org.springframework.data.repository.CrudRepository;

import com.borisns.PTA.entities.Product;

public interface ProductRepository extends CrudRepository<Product,Integer>{
	public Product findById(int id); 
}
