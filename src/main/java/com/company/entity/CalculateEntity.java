package com.company.entity;

import com.company.enums.CategoryStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "calc")
@ToString
public class CalculateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer categoryId;

    @Column
    private Double value1;

    @Column
    private Double value2;

    @Column
    private Double value3;

    @Column
    @Enumerated(EnumType.STRING)
    private CategoryStatus status = CategoryStatus.ACTIVE;

    @Column(name = "profile_id", nullable = false)
    private Integer profileId;

}
