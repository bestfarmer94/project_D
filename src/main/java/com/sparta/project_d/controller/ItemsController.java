package com.sparta.project_d.controller;

import com.sparta.project_d.dto.CrystalDto;
import com.sparta.project_d.dto.ItemsListDto;
import com.sparta.project_d.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemsController {

    private final ItemService itemService;

    @GetMapping("/items")
    public ItemsListDto getItems() {
        return itemService.getItems();
    }

    @PutMapping("/crystal")
    public ResponseEntity saveCrystal(@Valid @RequestBody CrystalDto crystalDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(itemService.saveCrystal(crystalDto));
    }
//    @PutMapping("/items")
//    public ItemsListDto updateItems() {
//        return
//    }
}
