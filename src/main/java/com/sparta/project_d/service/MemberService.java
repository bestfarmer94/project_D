package com.sparta.project_d.service;

import com.sparta.project_d.dto.MemberDto;
import com.sparta.project_d.dto.ResponseDto;
import com.sparta.project_d.entity.Member;
import com.sparta.project_d.exception.CustomException;
import com.sparta.project_d.exception.ErrorCode;
import com.sparta.project_d.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public ResponseDto<String> saveMember(MemberDto memberDto) {
        String message;
        Optional<Member> member = memberRepository.findByName(memberDto.getName());
        if (member.isPresent()) {
            member.get().update(memberDto);
            message = "영지 정보 수정 완료";
        } else {
            memberRepository.save(Member.builder().memberDto(memberDto).build());
            message = "영지 정보 저장 완료";
        }

        return ResponseDto.success(message);
    }


    @Transactional(readOnly = true)
    public ResponseDto<MemberDto> loadMember(String name) {
        return ResponseDto.success(MemberDto.of(getMember(name)));
    }

    public Member getMember(String name) {
        return memberRepository.findByName(name).orElseThrow(
                () -> new CustomException(ErrorCode.NotFoundUsers));
    }
}
