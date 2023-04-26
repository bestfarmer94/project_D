package com.sparta.project_d.controller;

import com.sparta.project_d.dto.CrystalDto;
import com.sparta.project_d.dto.ItemsListDto;
import com.sparta.project_d.dto.ResponseDto;
import com.sparta.project_d.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemsController {

    private final ItemService itemService;


    @GetMapping("/items")
    public ResponseDto<ItemsListDto> getItems() {
        return itemService.getItems();
    }


    @PutMapping("/crystal")
    public ResponseDto<String> saveCrystal(@Valid @RequestBody CrystalDto crystalDto) {
        return itemService.saveCrystal(crystalDto);
    }


//    @PutMapping("/items")
//    public ResponseEntity updateItems(@Valid @RequestBody ItemsListDto itemsListDto, BindingResult bindingResult) {
//        if(bindingResult.hasErrors()){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
//        }
//        return ResponseEntity.ok(itemService.updateItems(itemsListDto));
//    }
}
