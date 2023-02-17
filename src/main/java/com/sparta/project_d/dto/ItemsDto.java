package com.sparta.project_d.dto;

import com.sparta.project_d.Enum.Category;
import com.sparta.project_d.Enum.Grade;
import com.sparta.project_d.Enum.PriceType;
import com.sparta.project_d.entity.Items;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Getter
@NoArgsConstructor
public class ItemsDto{
    private String itemName;
    private int price;
    private String image;
    private Category category;
    private Grade grade;

    public ItemsDto(JSONObject itemJason, PriceType priceType) {
        this.itemName = itemJason.getString("Name");
        this.price = itemJason.getInt(priceType.name());
        this.image = itemJason.getString("Icon");
        this.category = Category.valueOf(itemName);
    }

    public ItemsDto(Items item){
        this.itemName = item.getItemName();
        this.price = item.getPrice();
        this.image = item.getImage();
    }
}
