package com.sparta.project_d.service;

import com.sparta.project_d.dto.MemberDto;
import com.sparta.project_d.entity.Member;
import com.sparta.project_d.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public String saveMember(MemberDto memberDto) {
        String message = "";

        if(memberRepository.existsByName(memberDto.getName())){
            getMember(memberDto.getName()).update(memberDto);
            message = "영지 정보 수정 완료";
        }else{
            memberRepository.save(new Member(memberDto));
            message = "영지 정보 저장 완료";
        }

        return message;
    }

    @Transactional(readOnly = true)
    public MemberDto loadMember(String name) {
        return new MemberDto(getMember(name));
    }

    public Member getMember(String name){
        Member member = memberRepository.findByName(name).orElseThrow(
                () -> new IllegalArgumentException("저장된 정보가 없습니다.")
        );
        return member;
    }
}
