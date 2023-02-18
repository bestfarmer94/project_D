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
    protected String itemName;
    protected int price;
    protected String image;
    @Enumerated(EnumType.STRING)
    protected Grade grade;
    @Enumerated(EnumType.STRING)
    protected Category category;
}
