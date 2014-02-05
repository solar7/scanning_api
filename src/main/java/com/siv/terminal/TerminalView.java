package com.siv.terminal;

import java.util.List;
import java.util.Locale;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class TerminalView {
       
    private List<Item> items;
    
    private BigDecimal result;
    
    private StringBuilder out;
    
    public TerminalView() {
        Locale.setDefault(Locale.US);
        init();
    }
    
    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    public void setResult(BigDecimal result) {
        this.result = result;
    }
    
    public void make() {
        orderedItemsFormatter();
        resultFormatter();
    }
    
    public void init() {
        out = new StringBuilder();
    }
    
    public String getOutput() {
        return out.toString();
    }
    
    public void print() {
        System.out.println(out.toString());
    }
    
    private void orderedItemsFormatter() {
        out.append("Scan these items in this order: ");
        for (Item item:items) {
            out.append(item.getProductCode());
        }
        out.append("; ");
    }
    
    private void resultFormatter() {
        out.append("Verify the total price is ");      
        out.append(new DecimalFormat("$0.00").format(result));
        out.append(".");
    }
}
