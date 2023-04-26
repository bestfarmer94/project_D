package com.sparta.project_d.controller;

import com.sparta.project_d.dto.MemberDto;
import com.sparta.project_d.dto.ResponseDto;
import com.sparta.project_d.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/saveMember")
    public ResponseDto<String> saveMember(@Valid @RequestBody MemberDto memberDto) {
        return memberService.saveMember(memberDto);
    }


    @GetMapping("/loadMember/{name}")
    public ResponseDto<MemberDto> loadMember(@PathVariable @NotBlank String name) {
        return memberService.loadMember(name);
    }

}
