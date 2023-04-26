package com.sparta.project_d.dto;

import com.sparta.project_d.Enum.Category;
import com.sparta.project_d.Enum.Grade;
import com.sparta.project_d.Enum.PriceType;
import com.sparta.project_d.entity.Items;
import lombok.Builder;
import lombok.Getter;
import org.json.JSONObject;

import javax.validation.constraints.Positive;

@Getter
@Builder
public class ItemsDto implements Comparable<ItemsDto> {
    private String itemName;
    @Positive(message = "가격은 1이상의 정수로 입력해야 합니다.")
    private int price;
    private String image;
    private Category category;
    private Grade grade;


    public static ItemsDto of(Items items) {
        return ItemsDto.builder()
                .itemName(items.getItemName())
                .price(items.getPrice())
                .image(items.getImage())
                .category(items.getCategory())
                .grade(items.getGrade())
                .build();
    }


    public static ItemsDto ofJson(JSONObject itemJason, PriceType priceType, Category category) {
        return ItemsDto.builder()
                .itemName(itemJason.getString("Name"))
                .price(priceType == PriceType.CurrentMinPrice ? itemJason.getInt(priceType.name()) : (int) Math.round(itemJason.getDouble(priceType.name())))
                .image(itemJason.getString("Icon"))
                .category(category)
                .grade(Grade.valueOf(itemJason.getString("Grade")))
                .build();
    }


    @Override
    public int compareTo(ItemsDto o) {
        if (this.category == o.category) {
            return this.grade.compareTo(o.grade);
        }
        return this.category.compareTo(o.category);
    }
}
