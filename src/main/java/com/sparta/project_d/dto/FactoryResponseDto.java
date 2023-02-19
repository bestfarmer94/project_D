package com.sparta.project_d.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FactoryResponseDto {
    private String itemName;
    private String image;
    private int profit;
    private int dailyProfit;
    private int maintenance_day;
    private int maintenance_3day;
    private int purchaseEnergy;
}
