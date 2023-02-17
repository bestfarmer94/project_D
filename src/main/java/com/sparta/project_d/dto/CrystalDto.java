package com.sparta.project_d.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class CrystalDto {
    @NotNull @Positive
    private int price;
}
