package com.siv.terminal;

import java.util.Map;
import java.util.HashMap;

public class Catalog {
    
    private Map<String, Item> catalog = new HashMap<String, Item>();
    
    public void putItem(Item item) {
        catalog.put(item.getProductCode(), item);
    }
    
    public Item getItem(String code) {
        return catalog.get(code);
    }  

}
