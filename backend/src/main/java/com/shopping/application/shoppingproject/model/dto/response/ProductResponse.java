package com.shopping.application.shoppingproject.model.dto.response;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {

    private Integer id;

    private String productName;

    private String productDescription;

    private Long productPrice;

    private Integer stockQuantity;

    private String productStatus;

    private String productCategoryName;

}
