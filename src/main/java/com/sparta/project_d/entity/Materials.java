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
public class Materials extends Items{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void update(ItemsDto itemsDto) {
        this.price = itemsDto.getPrice();
    }
}
