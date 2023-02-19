package com.sparta.project_d.dto;

import com.sparta.project_d.entity.Crystal;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class CrystalDto {
    @Positive
    private int price;

    public CrystalDto(Crystal crystal) {
        this.price = crystal.getPrice();
    }
}
