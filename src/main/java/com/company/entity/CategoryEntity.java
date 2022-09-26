package com.company.entity;

import com.company.enums.CategoryStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryEntity {

    private Integer id;
    private String nameUz;
    private String nameRu;
    private Boolean visible;
    private CategoryStatus status = CategoryStatus.ACTIVE;


}
