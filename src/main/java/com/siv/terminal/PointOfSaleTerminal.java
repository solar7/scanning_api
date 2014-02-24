package com.siv.terminal;

import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.math.BigDecimal;

public class PointOfSaleTerminal implements SaleTerminal {

    private Catalog catalog;
    
    private List<Item> itemsInCart = new LinkedList<Item>();
    
    public void setPricing(Catalog catalog) {
        this.catalog = catalog;
    }
    
    public List<Item> getItems() {
        return itemsInCart;
    }

    public void clear() {
        itemsInCart.clear();
    }
 
    public void scan(String itemCode) throws ItemNotFoundException {
        Item item = catalog.getItem(itemCode);
        if (item != null) {
            itemsInCart.add(item);
        } else {
            throw new ItemNotFoundException("Item with code '" +
                    itemCode + "' hasn't been found in Catalog");
        }
    }
    
    public BigDecimal calculateTotal() {
        BigDecimal total = new BigDecimal(0);
        Set<String> countedCodes = new HashSet<String>();       
        for (Item item:itemsInCart) {
            if (item.hasDiscount()) {
                String code = item.getProductCode();               
                if (countedCodes.contains(code)) {
                    //this item already counted
                    continue;
                }
                List<Item> similarItems = Utils.doFilter(itemsInCart, code);
                int discountQuantity = item.getDiscount().getQuantity();
                int quantity = similarItems.size() / discountQuantity;
                if (quantity > 0) {
                    BigDecimal discountPrice = item.getDiscount().getPrice();
                    BigDecimal discountedTotal = new BigDecimal(quantity).multiply(discountPrice);
                    total = total.add(discountedTotal);
                    int remain = similarItems.size() % discountQuantity;
                    if (remain > 0) {
                        BigDecimal remainTotal = new BigDecimal(remain).multiply(item.getPrice());
                        total = total.add(remainTotal);
                    }
                    //mark as counted
                    countedCodes.add(code);
                } else {
                    total = total.add(item.getPrice());
                }
            } else {
                total = total.add(item.getPrice());
            }
        }  
        return total;
    }

}
