package com.sparta.project_d.dto;

import com.sparta.project_d.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class MemberDto {
    @NotNull @Size(max = 50)
    private String name;
    @Min(100)
    private int recovery;
    @Positive @Max(99)
    private double energyReduce;
    @Positive @Max(99)
    private double goldReduce;
    @Positive @Max(99)
    private double timeReduce;

    public MemberDto(Member member){
        this.name = member.getName();
        this.recovery = member.getRecovery();
        this.energyReduce = member.getEnergyReduce();
        this.goldReduce = member.getGoldReduce();
        this.timeReduce = member.getTimeReduce();
    }
}
