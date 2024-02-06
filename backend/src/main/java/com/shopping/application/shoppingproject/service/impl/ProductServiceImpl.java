package com.shopping.application.shoppingproject.service.impl;

import com.shopping.application.shoppingproject.mapper.ProductMapper;
import com.shopping.application.shoppingproject.model.dto.requst.ProductRequest;
import com.shopping.application.shoppingproject.model.dto.response.ProductResponse;
import com.shopping.application.shoppingproject.model.entity.Category;
import com.shopping.application.shoppingproject.model.entity.Product;
import com.shopping.application.shoppingproject.repository.CategoryRepository;
import com.shopping.application.shoppingproject.repository.ProductRepository;
import com.shopping.application.shoppingproject.service.ProductService;
import com.shopping.application.shoppingproject.specification.ProductShoppingSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper;


    @Override
    public List<ProductResponse> filterProduct(String productName, Integer min, Integer max, Integer stockQuantity, String categoryName) {
        Specification<Product> filterByProductName = ProductShoppingSpecification.filterByProductName(productName);
        Specification<Product> filterByPrice = ProductShoppingSpecification.filterByPrice(min, max);
        Specification<Product> filterByLessThanStockQuantity = ProductShoppingSpecification.filterByLessThanStockQuantity(stockQuantity);
        Specification<Product> filterByGreaterThanStockQuantity = ProductShoppingSpecification.filterByGreaterThanStockQuantity(stockQuantity);
        Specification<Product> filterByCategoryName = ProductShoppingSpecification.filterByCategoryName(categoryName);
        Specification<Product> specification = Specification.where(filterByProductName)
                .and(filterByPrice)
                .and(filterByLessThanStockQuantity)
                .and(filterByGreaterThanStockQuantity)
                .and(filterByCategoryName);

        List<Product> allProduct = productRepository.findAll(specification);

        return allProduct
                .stream()
                .map(productMapper::productToProductResponse)
                .toList();

    }

    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.productRequestToProduct(productRequest);
        String categoryName = productRequest.getCategoryName();
        Category category = categoryRepository.findCategoryByCategoryName(categoryName.toLowerCase()).orElseThrow();
        product.setCategoryId(category);
        category.addProduct(product);

        Product savedProduct = productRepository.save(product);

        return ProductResponse
                .builder()
                .productStatus(savedProduct.getProductStatus().name())
                .productName(savedProduct.getName())
                .productPrice(savedProduct.getPrice())
                .productCategoryName(savedProduct.getCategoryId().getCategoryName())
                .productDescription(savedProduct.getDescription())
                .stockQuantity(savedProduct.getStockQuantity())
                .id(savedProduct.getId())
                .build();

    }



    public ProductResponse getProductById(int id) {
        Product product = this.productRepository.findById(id).orElseThrow();
        return ProductResponse
                .builder()
                .id(product.getId())
                .stockQuantity(product.getStockQuantity())
                .productStatus(product.getProductStatus().name())
                .productPrice(product.getPrice())
                .productDescription(product.getDescription())
                .productName(product.getName())
                .productCategoryName(product.getCategoryId().getCategoryName())
                .build();
    }



}
