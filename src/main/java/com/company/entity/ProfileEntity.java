package com.company.entity;

import com.company.enums.CategoryStatus;
import com.company.enums.Language;
import com.company.enums.ProfileStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "profile")
@Entity
@ToString
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "u_name")
    private String username;

    @Column
    private Boolean visible;

    @Column
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

    @Column(name = "lang")
    @Enumerated(EnumType.STRING)
    private Language language;

}
