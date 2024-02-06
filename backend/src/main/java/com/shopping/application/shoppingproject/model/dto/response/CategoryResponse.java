package com.shopping.application.shoppingproject.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {
    private String categoryName;
}
