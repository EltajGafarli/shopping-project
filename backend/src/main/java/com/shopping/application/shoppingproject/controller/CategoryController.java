package com.shopping.application.shoppingproject.controller;

import com.shopping.application.shoppingproject.model.dto.requst.CategoryRequest;
import com.shopping.application.shoppingproject.model.dto.response.CategoryResponse;
import com.shopping.application.shoppingproject.model.enums.UserRole;
import com.shopping.application.shoppingproject.service.CategoryService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RestController
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping(path = "/create")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        log.info("Category Response It works perfectly");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        categoryService
                                .addCategory(categoryRequest)
                );
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAllCategories() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryService.findAllCategories());
    }
}
