package com.sparta.project_d.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemsListDto {
    List<ItemsDto> materialsDtoList;
    List<ItemsDto> productsDtoList;
}
