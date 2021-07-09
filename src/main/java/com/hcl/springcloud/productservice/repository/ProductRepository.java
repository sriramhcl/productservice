package com.hcl.springcloud.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.springcloud.productservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
