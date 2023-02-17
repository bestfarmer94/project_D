package com.sparta.project_d.entity;

import com.sparta.project_d.Enum.Category;
import com.sparta.project_d.Enum.Grade;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
public class Items {
    private String itemName;
    protected int price;
    private String image;
    @Enumerated(EnumType.STRING)
    private Grade grade;
    @Enumerated(EnumType.STRING)
    private Category category;

}
