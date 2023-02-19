package com.sparta.project_d.controller;

import com.sparta.project_d.dto.FactoryRequestDto;
import com.sparta.project_d.dto.ItemsListDto;
import com.sparta.project_d.service.FactoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FactoryController {

    private final FactoryService factoryService;

    @PostMapping("/factory")
    public ResponseEntity setFactory(@Valid @RequestBody FactoryRequestDto factoryRequestDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        return ResponseEntity.ok(factoryService.calculate(factoryRequestDto));
    }
}
