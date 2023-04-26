package com.sparta.project_d.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ItemsListDto {
    List<ItemsDto> materialsDtoList;
    List<ItemsDto> productsDtoList;
    CrystalDto crystalDto;

    public static ItemsListDto of(List<ItemsDto> materialsDtoList, List<ItemsDto> productsDtoList, CrystalDto crystalDto) {
        return ItemsListDto.builder()
                .materialsDtoList(materialsDtoList)
                .productsDtoList(productsDtoList)
                .crystalDto(crystalDto)
                .build();
    }
}
