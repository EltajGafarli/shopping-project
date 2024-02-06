package com.shopping.application.shoppingproject.model.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum ProductStatus {

    AVAILABLE(1), SELECTED(2), BOUGHT(3), OUT_OF_STOCK(4);

    private final Integer id;
    private static final Map<Integer, ProductStatus> idToProductStatusMap = new HashMap<>();

    @JsonCreator
    public static ProductStatus forValue(String value) {
        for (ProductStatus status : ProductStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }

    static {
        for (ProductStatus status : values()) {
            idToProductStatusMap.put(status.id, status);
        }
    }

    public static ProductStatus fromId(Integer id) {
        return idToProductStatusMap.getOrDefault(id, OUT_OF_STOCK);
    }

}
