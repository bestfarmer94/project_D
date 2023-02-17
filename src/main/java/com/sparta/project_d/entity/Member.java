package com.sparta.project_d.entity;

import com.sparta.project_d.dto.MemberDto;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int recovery;
    private double energyReduce;
    private double goldReduce;
    private double timeReduce;
    private final int recovery_base = 100;

    public Member() {
        this.recovery = recovery_base;
    }

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
