package com.sparta.project_d.Enum;

import lombok.Getter;

@Getter
public enum Category {
    고고학(90700, "", 0),
    낚시(90600, "", 1),
    수렵(90500, "", 2),
    융화(50000, "융화", 0);

    private final int code;
    private final String itemName;
    private final int unit;

    Category(int code, String itemName, int unit) {
        this.code = code;
        this.itemName = itemName;
        this.unit = unit;
    }
}