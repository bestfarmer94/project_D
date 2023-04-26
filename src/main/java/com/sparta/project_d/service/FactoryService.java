package com.sparta.project_d.service;

import com.sparta.project_d.Enum.Factory;
import com.sparta.project_d.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FactoryService {

    public ResponseDto<List<FactoryResponseDto>> calculate(FactoryRequestDto factoryRequestDto) {
        MemberDto member = factoryRequestDto.getMemberDto();
        ItemsListDto itemsListDto = factoryRequestDto.getItemsListDto();
        List<ItemsDto> materialsDtoList = itemsListDto.getMaterialsDtoList();
        List<ItemsDto> productsDtoList = itemsListDto.getProductsDtoList();
        CrystalDto crystalDto = itemsListDto.getCrystalDto();

        List<FactoryResponseDto> factoryDtoList = new ArrayList<>();
        for (Factory factory : Factory.values()) {
            // 생산품 객체.
            ItemsDto product = productsDtoList.get(factory.getProductId());
            // 재료품 객체.
            int unit = factory.getCategory().getUnit() * 3;
            ItemsDto normal = materialsDtoList.get(unit);
            ItemsDto uncommon = materialsDtoList.get(unit + 1);
            ItemsDto rare = materialsDtoList.get(unit + 2);

            // 생산품 이름
            String itemName = factory.getItemName();
            // 생산품 이미지
            String image = product.getImage();
            // 생산비
            double productCost = Math.floor(factory.getProduceCost() * (100 - member.getGoldReduce()) / 100.0)
                    + factory.getNormalQuantity() * normal.getPrice()
                    + factory.getUncommonQuantity() * uncommon.getPrice()
                    + factory.getRareQuantity() * rare.getPrice();
            // 수수료
            int fee = (int) Math.ceil((product.getPrice() - 1) / 20.0);
            // 단위별 매출
            double sales = 1.05 * factory.getProduceQuantity() * (product.getPrice() - fee);
            // 활동력 소모량
            double energyCost = Math.floor(factory.getEnergyCost() * (100 - member.getEnergyReduce()) / 100.0);
            // 단위별 순수익
            double profit = sales - productCost;
            // 일일 생산량
            double dailyQuantity = 24 * 6 * member.getRecovery() / energyCost;
            // 일일 순수익
            int dailyProfit = (int) Math.ceil(profit * dailyQuantity);
            // 생산 유지비
            double maintenance_day = 1.3 * sales * dailyQuantity * (product.getPrice() / (double) (product.getPrice() - fee)) + 40 * productCost;
            int maintenance_3day = (int) Math.ceil(3 * maintenance_day - 2 * 40 * productCost);
            // 활동력 구매 수익
            int purchaseEnergy = (int) Math.round(30000 / energyCost * profit - 1.44 * crystalDto.getPrice() * 20 / 19.0);

            factoryDtoList.add(FactoryResponseDto.builder()
                    .itemName(itemName)
                    .image(image)
                    .profit((int) profit)
                    .dailyProfit(dailyProfit)
                    .maintenance_day((int) maintenance_day)
                    .maintenance_3day(maintenance_3day)
                    .purchaseEnergy(purchaseEnergy)
                    .build());
        }

        return ResponseDto.success(factoryDtoList);
    }
}
