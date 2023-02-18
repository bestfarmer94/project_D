package com.sparta.project_d.util;

import com.sparta.project_d.Enum.Category;
import com.sparta.project_d.Enum.Items;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ItemChecker {
    private Map<String, Category> neededItemMap = new HashMap();

    ItemChecker() {
        for (Items item : Items.values()) {
            neededItemMap.put(item.getItemName(), item.getCategory());
        }
    }

    public boolean isItemNeeded(String itemName) {
        return neededItemMap.containsKey(itemName);
    }

    public Category getCategory(String itemName){
        return neededItemMap.get(itemName);
    }
}
