package com.sparta.project_d.controller;

import com.sparta.project_d.service.FactoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FactoryController {

    private final FactoryService factoryService;
}
