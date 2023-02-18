package com.sparta.project_d.controller;

import com.sparta.project_d.dto.MemberDto;
import com.sparta.project_d.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/saveMember")
    public ResponseEntity saveMember(@Valid @RequestBody MemberDto memberDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(memberService.saveMember(memberDto));
    }

    @GetMapping("/loadMember/{name}")
    public MemberDto loadMember(@PathVariable String name) {
        System.out.println(name);
        return memberService.loadMember(name);
    }

}
