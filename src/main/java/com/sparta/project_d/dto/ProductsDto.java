package com.sparta.project_d.dto;

import lombok.Getter;

@Getter
public class ProductsDto extends ItemsDto{
    private String itemName;
    private int price;
    private String image;
}
