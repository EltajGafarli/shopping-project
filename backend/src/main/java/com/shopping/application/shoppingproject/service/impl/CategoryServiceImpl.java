package com.shopping.application.shoppingproject.service.impl;

import com.shopping.application.shoppingproject.error.AlreadyExistException;
import com.shopping.application.shoppingproject.model.dto.requst.CategoryRequest;
import com.shopping.application.shoppingproject.model.dto.response.CategoryResponse;
import com.shopping.application.shoppingproject.model.entity.Category;
import com.shopping.application.shoppingproject.repository.CategoryRepository;
import com.shopping.application.shoppingproject.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public CategoryResponse addCategory(CategoryRequest categoryRequest) {
        Optional<Category> categoryByCategoryName = categoryRepository.findCategoryByCategoryName(categoryRequest.getCategoryName());
        if(categoryByCategoryName.isPresent()) {
            throw new AlreadyExistException(categoryRequest.getCategoryName() + " already exist");
        }
        Category category = Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .build();

        categoryRepository.save(category);
        return CategoryResponse.builder()
                .categoryName(category.getCategoryName())
                .build();
    }

    @Override
    public List<CategoryResponse> findAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(
                category -> CategoryResponse
                        .builder()
                        .categoryName(category.getCategoryName())
                        .build()
                )
                .toList();
    }


}
