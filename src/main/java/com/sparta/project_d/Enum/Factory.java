package com.sparta.project_d.Enum;

import lombok.Getter;

@Getter
public enum Factory {
    오레하_융화재료("오레하 융화 재료", Category.고고학, 1, 205, 30, 216, 0.64, 2.6, 0.8),
    상급_융화재료("상급 오레하", Category.고고학, 2, 250, 20, 288, 0.94, 2.9, 1.6),
    최상급_고고학("최상급(고고학)", Category.고고학, 3, 300, 15, 360, 1.07, 5.1, 5.2),
    최상급_낚시("최상급(낚시)", Category.낚시, 3, 300, 15, 360, 1.42, 6.9, 5.2),
    최상급_수렵("최상급(수렵)", Category.수렵, 3, 300, 15, 360, 1.42, 6.9, 5.2);

    private final String itemName;
    private final Category category;
    private final int productId;
    private final int produceCost;
    private final int produceQuantity;
    private final int energyCost;
    private final double normalQuantity;
    private final double uncommonQuantity;
    private final double rareQuantity;

    Factory(String itemName, Category category, int productId, int produceCost, int produceQuantity, int energyCost,
            double normalQuantity, double uncommonQuantity, double rareQuantity) {
        this.itemName = itemName;
        this.category = category;
        this.productId = productId;
        this.produceCost = produceCost;
        this.produceQuantity = produceQuantity;
        this.energyCost = energyCost;
        this.normalQuantity = normalQuantity;
        this.uncommonQuantity = uncommonQuantity;
        this.rareQuantity = rareQuantity;
    }
}
