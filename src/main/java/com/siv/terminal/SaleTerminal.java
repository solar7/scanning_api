package com.siv.terminal;

import java.util.Map;
import java.math.BigDecimal;

/**
 * Point-of-sale scanning API
 * 
 */

public interface SaleTerminal {
    
    void scan(String itemCode);
    
    void setPricing(Map<String, Item> priceCatalog);
    
    BigDecimal calculateTotal();

}
