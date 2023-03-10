package com.sparta.project_d.open.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.project_d.Enum.PriceType;
import com.sparta.project_d.dto.ItemsDto;
import com.sparta.project_d.dto.ItemsListDto;
import com.sparta.project_d.open.service.OpenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OpenController {

    private final OpenService openService;

    @GetMapping("/search")
    public List<ItemsDto> searchItems() throws JsonProcessingException {
        return openService.searchRedis();
    }

    @GetMapping("/admin")
    public void admin() throws InterruptedException {
        openService.updateItems();
    }
}
