package com.sparta.project_d.dto;

import com.sparta.project_d.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@Builder
public class MemberDto {
    @NotBlank(message = "이름은 공백일 수 없습니다.")
    @Size(max = 50, message = "이름은 50자를 초과할 수 없습니다.")
    private String name;

    @Min(value = 100, message = "최소 활동력 회복량은 100입니다.")
    private int recovery;

    @PositiveOrZero(message = "활동력 소모량 감소는 음수일 수 없습니다.")
    @Max(value = 99, message = "활동력 소모량 감소는 0 ~ 99 이내입니다.")
    private double energyReduce;

    @PositiveOrZero(message = "골드 소모량 감소는 음수일 수 없습니다.")
    @Max(value = 99, message = "골드 소모량 감소는 0 ~ 99 이내입니다.")
    private double goldReduce;

    @PositiveOrZero(message = "시간 소모량 감소는 음수일 수 없습니다.")
    @Max(value = 99, message = "시간 소모량 감소는 0 ~ 99 이내입니다.")
    private double timeReduce;


    public static MemberDto of(Member member) {
        return MemberDto.builder()
                .name(member.getName())
                .recovery(member.getRecovery())
                .energyReduce(member.getEnergyReduce())
                .goldReduce(member.getGoldReduce())
                .timeReduce(member.getTimeReduce())
                .build();
    }
}
