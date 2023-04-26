package com.sparta.project_d.controller;

import com.sparta.project_d.dto.FactoryRequestDto;
import com.sparta.project_d.dto.FactoryResponseDto;
import com.sparta.project_d.dto.ResponseDto;
import com.sparta.project_d.service.FactoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FactoryController {

    private final FactoryService factoryService;


    @PostMapping("/factory")
    public ResponseDto<List<FactoryResponseDto>> setFactory(@Valid @RequestBody FactoryRequestDto factoryRequestDto){
        return factoryService.calculate(factoryRequestDto);
    }
}
