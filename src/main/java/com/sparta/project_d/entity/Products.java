package com.sparta.project_d.entity;

import com.sparta.project_d.dto.ItemsDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Products extends Items implements Comparable<Products>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Builder
    public Products(ItemsDto itemsDto) {
        this.itemName = itemsDto.getItemName();
        this.price = itemsDto.getPrice();
        this.image = itemsDto.getImage();
        this.category = itemsDto.getCategory();
        this.grade = itemsDto.getGrade();
    }


    public void update(ItemsDto itemsDto) {
        this.price = itemsDto.getPrice();
    }


    @Override
    public int compareTo(Products o) {
        if(this.category == o.category){
            return this.grade.compareTo(o.grade);
        }
        return this.category.compareTo(o.category);
    }
}
