package com.sparta.project_d.entity;

import com.sparta.project_d.dto.MemberDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int recovery;

    @Column(nullable = false)
    private double energyReduce;

    @Column(nullable = false)
    private double goldReduce;

    @Column(nullable = false)
    private double timeReduce;


    @Builder
    public Member(MemberDto memberDto) {
        this.name = memberDto.getName();
        this.recovery = memberDto.getRecovery();
        this.energyReduce = memberDto.getEnergyReduce();
        this.goldReduce = memberDto.getGoldReduce();
        this.timeReduce = memberDto.getTimeReduce();
    }


    public void update(MemberDto memberDto) {
        this.recovery = memberDto.getRecovery();
        this.energyReduce = memberDto.getEnergyReduce();
        this.goldReduce = memberDto.getGoldReduce();
        this.timeReduce = memberDto.getTimeReduce();
    }
}
