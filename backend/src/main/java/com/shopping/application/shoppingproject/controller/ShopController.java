package com.shopping.application.shoppingproject.controller;

import com.shopping.application.shoppingproject.model.dto.requst.ProductRequest;
import com.shopping.application.shoppingproject.model.dto.response.ProductResponse;
import com.shopping.application.shoppingproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api/shopping")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RestController
public class ShopController {

    private final ProductService productService;


    @GetMapping(path = "/search")
    public List<ProductResponse> filterProduct(
            @RequestParam("productName") String productName,
            @RequestParam("min") Integer min,
            @RequestParam("max") Integer max,
            @RequestParam("stockQuantity") Integer stockQuantity,
            @RequestParam("categoryName") String categoryName
    ) {


        return this.productService
                .filterProduct(
                        productName,
                        min,
                        max,
                        stockQuantity,
                        categoryName);
    }


    @PostMapping(path = "/create")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse product = productService.createProduct(productRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(product);
    }

}
