package com.shopping.application.shoppingproject.mapper;


import com.shopping.application.shoppingproject.model.dto.requst.ProductRequest;
import com.shopping.application.shoppingproject.model.dto.response.ProductResponse;
import com.shopping.application.shoppingproject.model.entity.Product;
import com.shopping.application.shoppingproject.model.enums.ProductStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProductMapper {

    public ProductResponse productToProductResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getName())
                .productDescription(product.getDescription())
                .productStatus(product.getProductStatus().name())
                .productPrice(product.getPrice())
                .productCategoryName(product.getCategoryId().getCategoryName())
                .stockQuantity(product.getStockQuantity())
                .build();
    }

    public Product productRequestToProduct(ProductRequest productRequest) {
        return Product
                .builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .productStatus(productRequest.getProductStatus())
                .description(productRequest.getDescription())
                .stockQuantity(productRequest.getStockQuantity())
                .build();
    }

    public ProductResponse requestToResponse(ProductRequest productRequest) {
        return ProductResponse
                .builder()
                .productName(productRequest.getName())
                .productDescription(productRequest.getDescription())
                .productPrice(productRequest.getPrice())
                .productCategoryName(productRequest.getCategoryName())
                .productStatus(productRequest.getProductStatus().name())
                .stockQuantity(productRequest.getStockQuantity())
                .build();

    }

    public ProductRequest responseToRequest(ProductResponse productResponse) {
        return ProductRequest
                .builder()
                .name(productResponse.getProductName())
                .productStatus(ProductStatus.valueOf(productResponse.getProductStatus()))
                .categoryName(productResponse.getProductCategoryName())
                .description(productResponse.getProductDescription())
                .stockQuantity(productResponse.getStockQuantity())
                .price(productResponse.getProductPrice())
                .build();
    }
}
