package com.company.entity;

import com.company.enums.CategoryStatus;
import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CalculateEntity {

    private Integer id;
    private Integer categoryId;
    private Double value1;
    private Double value2;
    private Double value3;
    private CategoryStatus status = CategoryStatus.ACTIVE;
    private Integer profileId;

}
