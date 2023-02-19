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
public class Materials extends Items implements Comparable<Materials>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Materials(ItemsDto itemsDto) {
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
    public int compareTo(Materials o) {
        if(this.category == o.category){
            return this.grade.compareTo(o.grade);
        }
        return this.category.compareTo(o.category);
    }
}
