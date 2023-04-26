package com.sparta.project_d.dto;

import com.sparta.project_d.entity.Crystal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrystalDto {
    @Positive(message = "가격은 1이상의 정수로 입력해야 합니다.")
    private int price;

    public static CrystalDto of(Crystal crystal) {
        return CrystalDto.builder()
                .price(crystal.getPrice())
                .build();
    }
}
