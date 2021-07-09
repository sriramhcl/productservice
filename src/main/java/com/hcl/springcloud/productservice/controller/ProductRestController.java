package com.hcl.springcloud.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hcl.springcloud.productservice.model.Coupon;
import com.hcl.springcloud.productservice.model.Product;
import com.hcl.springcloud.productservice.repository.ProductRepository;

@RestController
@RequestMapping(value = "/product")
public class ProductRestController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${couponService.url}")
	private String couponServiceUrl;

	@RequestMapping(value = "/createProduct", method = RequestMethod.POST)
	public Product createProduct(@RequestBody Product product) {
		Coupon coupon = restTemplate.getForObject(couponServiceUrl + "?couponCode=" + product.getCouponCode(),
				Coupon.class);
		product.setPrice(product.getPrice() - coupon.getDiscount());
		productRepository.save(product);

		return product;
	}

}
