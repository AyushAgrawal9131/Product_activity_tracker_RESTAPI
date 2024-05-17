package com.borisns.PTA.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.borisns.PTA.entities.Product;
import com.borisns.PTA.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productservice;
	
	// get all products handler
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProduct() {

        List<Product> list = productservice.getAllProducts();
        if (list.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }
    
 // get single book handler
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getBook(@PathVariable("id") int id) {
        Product product = productservice.getProductById(id);
        if (product== null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(product));
    }
    
 // new product handler
    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product p = null;
        try {
            p = this.productservice.addProduct(product);
            System.out.println(product);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") int productId) {
        try {
            this.productservice.deleteProduct(productId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    
 // update book handler
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable("productId") int productId) {
        try {
            this.productservice.updateProduct(product, productId);
            return ResponseEntity.ok().body(product);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

       
    }
    
    
	

}

