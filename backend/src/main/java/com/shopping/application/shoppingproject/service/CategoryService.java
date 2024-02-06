package com.shopping.application.shoppingproject.service;

import com.shopping.application.shoppingproject.model.dto.requst.CategoryRequest;
import com.shopping.application.shoppingproject.model.dto.response.CategoryResponse;
import com.shopping.application.shoppingproject.model.entity.Category;
import com.shopping.application.shoppingproject.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryResponse addCategory(CategoryRequest categoryRequest);
    List<CategoryResponse> findAllCategories();
}
