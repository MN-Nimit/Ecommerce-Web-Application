package com.backend.ecommerce.request;

import com.backend.ecommerce.entity.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
public class CreateProductRequest {

    private String title;

    private String description;

    private int price;

    private int discountedPrice;

    private int discountPercentage;

    private int quantity;

    private String brand;

    private String color;

    private Set<Size> sizes = new HashSet<>();

    private String imageUrl;

    private String topLevelCategory;

    private String secondLevelCategory;

    private String thirdLevelCategory;
}
