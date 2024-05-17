package com.borisns.PTA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.borisns.PTA.Repo.ProductRepository;
import com.borisns.PTA.entities.Product;

@Component
public class ProductService {
	@Autowired
    private ProductRepository productrepository;
	public List<Product> getAllProducts() {
        List<Product> list=(List<Product>)this.productrepository.findAll();
        return list;
    }
	
	 public Product getProductById(int id) {
	        Product product = null;
	        try {
	            product=this.productrepository.findById(id);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return product;
	   }
	 
	 public Product addProduct(Product p) {
	        Product result=productrepository.save(p);
	        return result;
	    }
	 public void deleteProduct(int pid) {

	        productrepository.deleteById(pid);
	    }
	 public void updateProduct(Product product, int productId) {
	        

	        product.setId(productId);
	        productrepository.save(product);
	    }
	 
	 

}