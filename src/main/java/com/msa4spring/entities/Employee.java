package com.msa4spring.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    private Long empId;
    private String name;
    private String birth;
    private String gender;
    private String hireAt;
    private String fireAt;
    private Long supId;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;
}
