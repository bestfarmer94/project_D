package com.sparta.project_d.dto;

import com.sparta.project_d.Enum.Category;
import com.sparta.project_d.Enum.Grade;
import com.sparta.project_d.Enum.PriceType;
import com.sparta.project_d.entity.Items;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class ItemsDto implements Comparable<ItemsDto> {
    private String itemName;
    @Positive
    private int price;
    private String image;
    private Category category;
    private Grade grade;

    public ItemsDto(JSONObject itemJason, PriceType priceType, Category category) {
        this.itemName = itemJason.getString("Name");
        this.price = priceType == PriceType.CurrentMinPrice ? itemJason.getInt(priceType.name()) : (int) Math.round(itemJason.getDouble(priceType.name()));
        this.image = itemJason.getString("Icon");
        this.category = category;
        this.grade = Grade.valueOf(itemJason.getString("Grade"));
    }

    public ItemsDto(Items item) {
        this.itemName = item.getItemName();
        this.price = item.getPrice();
        this.image = item.getImage();
        this.category = item.getCategory();
        this.grade = item.getGrade();
    }

    @Override
    public int compareTo(ItemsDto o) {
        if (this.category == o.category) {
            return this.grade.compareTo(o.grade);
        }
        return this.category.compareTo(o.category);
    }
}
