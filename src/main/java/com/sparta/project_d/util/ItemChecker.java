package com.sparta.project_d.util;

import com.sparta.project_d.Enum.ItemName;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class ItemChecker {
    private HashSet<String> neededItemSet = new HashSet<>();

    ItemChecker() {
        for (ItemName itemName : ItemName.values()) {
            neededItemSet.add(itemName.getItemName());
        }
    }

    public boolean isItemNeeded(String itemName) {
        return neededItemSet.contains(itemName);
    }
}
