package com.shopping.application.shoppingproject.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category_sp")
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "category_sequence", sequenceName = "category_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sequence")
    @Column(name = "category_id")
    private Integer id;

    @Column(name = "category_name", unique = true)
    private String categoryName;

    @OneToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    }, mappedBy = "categoryId", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Product> products;


    public void addProduct(Product product) {
        products.add(product);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return Objects.equals(getId(), category.getId()) && Objects.equals(getCategoryName(),
                category.getCategoryName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCategoryName());
    }
}
