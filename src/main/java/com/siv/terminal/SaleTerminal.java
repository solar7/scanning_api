package com.siv.terminal;

import java.math.BigDecimal;

/**
 * Point-of-sale scanning API
 * 
 */

public interface SaleTerminal {
    
    void scan(String itemCode) throws ItemNotFoundException;
    
    void setPricing(Catalog catalog);
    
    BigDecimal calculateTotal();

}
