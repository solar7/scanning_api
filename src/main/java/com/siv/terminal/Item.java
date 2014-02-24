package com.siv.terminal;

import java.math.BigDecimal;

public class Item {
       
    private String productCode;
    
    private BigDecimal price;
    
    private Discount discount;

    public Item(String code, BigDecimal price) {
        this.productCode = code;
        this.price = price;
    }
    
    public Item(String code, BigDecimal price, Discount discount) {
        this(code, price);
        this.discount = discount;
    }
       
    @Override
    public String toString() {
        return productCode + ", price = " + price + 
                " discount - " + (hasDiscount() ? "y" : "n"); 
    }
    
    public boolean hasDiscount() {
        return discount == null ? false : true;
    }
    
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Discount getDiscount() {
        return discount;
    }

}
