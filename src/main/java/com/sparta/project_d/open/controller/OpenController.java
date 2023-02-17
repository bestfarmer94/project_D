package com.sparta.project_d.open.controller;

import com.sparta.project_d.Enum.PriceType;
import com.sparta.project_d.dto.ItemsListDto;
import com.sparta.project_d.open.service.OpenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OpenController {

    private final OpenService openService;

    @GetMapping("/search")
    public ItemsListDto searchItems() {
        return openService.searchItems(PriceType.CurrentMinPrice);
    }

}
