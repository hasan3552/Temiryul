package com.company.entity;

import com.company.enums.CategoryStatus;
import com.company.enums.Language;
import com.company.enums.ProfileStatus;
import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProfileEntity {

    private Integer id;
    private String phoneNumber;
    private String userId;
    private String username;
    private Boolean visible;
    private ProfileStatus status;
    private Language language;

}
