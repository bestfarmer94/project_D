package com.sparta.project_d.service;

import com.sparta.project_d.dto.CrystalDto;
import com.sparta.project_d.dto.ItemsDto;
import com.sparta.project_d.dto.ItemsListDto;
import com.sparta.project_d.entity.Crystal;
import com.sparta.project_d.entity.Items;
import com.sparta.project_d.entity.Materials;
import com.sparta.project_d.entity.Products;
import com.sparta.project_d.repository.CrystalRepository;
import com.sparta.project_d.repository.MaterialsRepository;
import com.sparta.project_d.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final MaterialsRepository materialsRepository;
    private final ProductsRepository productsRepository;
    private final CrystalRepository crystalRepository;

    @Transactional(readOnly = true)
    public ItemsListDto getItems() {
        List<ItemsDto> materialsDtoList = new ArrayList<>();
        for (Items materials : getMaterials()) {
            materialsDtoList.add(new ItemsDto(materials));
        }

        List<ItemsDto> productsDtoList = new ArrayList<>();
        for (Items products : getProducts()) {
            productsDtoList.add(new ItemsDto(products));
        }

        List<Crystal> crystals = crystalRepository.findAll();

        return new ItemsListDto(materialsDtoList, productsDtoList, new CrystalDto(crystals.get(0)));
    }

    @Transactional
    public String saveCrystal(CrystalDto crystalDto) {
        List<Crystal> crystals = crystalRepository.findAll();
        crystals.get(0).update(crystalDto.getPrice());
        return "저장완료";
    }

    public List<Materials> getMaterials() {
        return materialsRepository.findAllByOrderByCategoryAscGradeAsc();
    }

    public List<Products> getProducts() {
        return productsRepository.findAllByOrderByGradeAsc();
    }
}
