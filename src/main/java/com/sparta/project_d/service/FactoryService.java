package com.sparta.project_d.service;

import com.sparta.project_d.Enum.Factory;
import com.sparta.project_d.dto.FactoryDto;
import com.sparta.project_d.dto.ItemsDto;
import com.sparta.project_d.entity.Crystal;
import com.sparta.project_d.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FactoryService {

    private final MemberService memberService;

    public List<FactoryDto> calculate(String name, List<ItemsDto> materialsList, List<ItemsDto> productsList, Crystal crystal) {
        List<FactoryDto> factoryDtoList = new ArrayList<>();
        Member member = memberService.getMember(name);

        for (Factory factory : Factory.values()) {
            // 생산품 객체.
            ItemsDto product = productsList.get(factory.getProductId());
            // 재료품 객체.
            int unit = factory.getCategory().getUnit() * 3;
            ItemsDto normal = materialsList.get(unit);
            ItemsDto uncommon = materialsList.get(unit + 1);
            ItemsDto rare = materialsList.get(unit + 2);

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
            int fee = (int) Math.ceil((product.getPrice() - 1) / 20);
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
            int purchaseEnergy = (int) Math.round(30000 / energyCost * profit - 1.44 * crystal.getPrice() * 20 / 19);

            factoryDtoList.add(new FactoryDto(itemName, image, (int) profit, dailyProfit, (int) maintenance_day, maintenance_3day, purchaseEnergy));
        }

        return factoryDtoList;
    }
}
