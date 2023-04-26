package com.sparta.project_d.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FactoryResponseDto {
    private String itemName;
    private String image;
    private int profit;
    private int dailyProfit;
    private int maintenance_day;
    private int maintenance_3day;
    private int purchaseEnergy;
}
