package com.shopping.application.shoppingproject.model.dto.requst;

import com.shopping.application.shoppingproject.model.enums.ProductStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {

    private String name;

    private String description;

    private Long price;

    private ProductStatus productStatus;
    private Integer stockQuantity;

    private String categoryName;

}
