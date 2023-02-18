package com.sparta.project_d.entity;

import com.sparta.project_d.dto.ItemsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Products extends Items{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
