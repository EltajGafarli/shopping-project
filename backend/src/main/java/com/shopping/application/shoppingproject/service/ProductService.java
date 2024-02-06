package com.shopping.application.shoppingproject.service;

import com.shopping.application.shoppingproject.model.dto.requst.ProductRequest;
import com.shopping.application.shoppingproject.model.dto.response.ProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<ProductResponse> filterProduct(String productName, Integer min, Integer max, Integer stockQuantity, String categoryName);
    ProductResponse createProduct(ProductRequest productRequest);

}
