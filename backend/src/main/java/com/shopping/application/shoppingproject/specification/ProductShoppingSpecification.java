package com.shopping.application.shoppingproject.specification;

import com.shopping.application.shoppingproject.model.entity.Category;
import com.shopping.application.shoppingproject.model.entity.Product;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ProductShoppingSpecification {
    public static Specification<Product> filterByProductName(String productName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), productName);
    }

    public static Specification<Product> filterByPrice(Integer min, Integer max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("price"), min, max);
    }

    public static Specification<Product> filterByGreaterThanStockQuantity(Integer stockQuantity) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("stockQuantity"), stockQuantity);
    }

    public static Specification<Product> filterByLessThanStockQuantity(Integer stockQuantity) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("stockQuantity"), stockQuantity);
    }

    public static Specification<Product> filterByCategoryName(String categoryName) {
        return (root, query, criteriaBuilder) -> {
            Join<Product, Category> categoryJoin = root.join("categoryId");
            return criteriaBuilder.equal(categoryJoin.get("categoryName"), categoryName);
        };
    }
}
