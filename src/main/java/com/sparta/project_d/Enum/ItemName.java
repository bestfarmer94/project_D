package com.sparta.project_d.Enum;

import lombok.Getter;

@Getter
public enum ItemName {
    하급_융화재료("하급 융화재료", Category.융화),
    중급_융화재료("중급 융화재료", Category.융화),
    상급_융화재료("상급 융화재료", Category.융화),
    최상급_융화재료("최상급 융화재료", Category.융화),
    고대_유물("고대 유물", Category.고고학),
    고급_유물("희귀한 유물", Category.고고학),
    희귀한_유물("오레하 유물", Category.고고학),
    생선("생선", Category.낚시),
    자연산_진주("자연산 진주", Category.낚시),
    오레하_태양_잉어("오레하 태양 잉어", Category.낚시),
    두툼한_생고기("두툼한 생고기", Category.수렵),
    질긴_가죽("질긴 가죽", Category.수렵),
    오레하_두툼한_생고기("오레하 두툼한 생고기", Category.수렵);

    String itemName;
    Category category;

    ItemName(String itemName, Category category) {
        this.itemName = itemName;
        this.category = category;
    }
}
