package com.sparta.project_d.open.dto;

import com.sparta.project_d.Enum.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BodyDto {
    private int CategoryCode;
    private String ItemName;

    public BodyDto(Category category){
        this.CategoryCode = category.getCode();
        this.ItemName = category.getItemName();
    }
}