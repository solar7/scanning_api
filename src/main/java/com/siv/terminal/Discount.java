package com.siv.terminal;

import java.math.BigDecimal;

public class Discount {

    private int quantity;
    
    private BigDecimal price;
    
    public Discount(int quantity, BigDecimal price) {
        this.quantity = quantity;
        this.price = price;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
