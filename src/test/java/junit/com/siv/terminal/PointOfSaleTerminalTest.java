package junit.com.siv.terminal;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

import com.siv.terminal.Discount;
import com.siv.terminal.Item;
import com.siv.terminal.PointOfSaleTerminal;
import com.siv.terminal.TerminalView;
import com.siv.terminal.ItemNotFoundException;

import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;

public class PointOfSaleTerminalTest {
    
    private static TerminalView terminalView = new TerminalView();
    
    private static PointOfSaleTerminal testTerminal = new PointOfSaleTerminal();
    
    @BeforeClass
    public static void initPriceCatalog() {
        Item itemA = new Item("A", new BigDecimal(1.25), new Discount(3, new BigDecimal(3)));
        Item itemB = new Item("B", new BigDecimal(4.25));
        Item itemC = new Item("C", new BigDecimal(1), new Discount(6, new BigDecimal(5)));
        Item itemD = new Item("D", new BigDecimal(0.75));
        
        Map<String, Item> priceCatalog = new HashMap<String, Item>();
        priceCatalog.put(itemA.getProductCode(), itemA);
        priceCatalog.put(itemB.getProductCode(), itemB);
        priceCatalog.put(itemC.getProductCode(), itemC);
        priceCatalog.put(itemD.getProductCode(), itemD);
        
        testTerminal.setPricing(priceCatalog);
    }
    
    @Before
    public void cleanup() {
        terminalView.init();
        testTerminal.clear();
    }
    
    @Test(expected = ItemNotFoundException.class)
    public void testScanNotContainsCode() throws ItemNotFoundException {
        testTerminal.scan("Z");
    }
    
    public void testScan1() {
        testTerminal.clear();
        try {
            testTerminal.scan("A");
            testTerminal.scan("B");
            testTerminal.scan("C");
            testTerminal.scan("D");
            testTerminal.scan("A");
            testTerminal.scan("B");
            testTerminal.scan("A");
        } catch (ItemNotFoundException e) {}
    }
    
    public void testScan2() {
        testTerminal.clear();
        try {
            testTerminal.scan("C");
            testTerminal.scan("C");
            testTerminal.scan("C");
            testTerminal.scan("C");
            testTerminal.scan("C");
            testTerminal.scan("C");
            testTerminal.scan("C");
        } catch (ItemNotFoundException e) {}
    }
    
    public void testScan3() {
        testTerminal.clear();
        try {
            testTerminal.scan("A");
            testTerminal.scan("B");
            testTerminal.scan("C");
            testTerminal.scan("D");
        } catch (ItemNotFoundException e) {}
    }
    
    @Test
    public void testCalculateTotal() {
        testScan1();
        assertEquals(new BigDecimal(13.25), testTerminal.calculateTotal());
        testScan2();
        assertEquals(new BigDecimal(6), testTerminal.calculateTotal());
        testScan3();
        assertEquals(new BigDecimal(7.25), testTerminal.calculateTotal());
    }
    
    @Test
    public void testView1() {
        testScan1();
        terminalView.setItems(testTerminal.getItems());
        terminalView.setResult(testTerminal.calculateTotal());
        terminalView.make(); terminalView.print();
        assertTrue(terminalView.getOutput().contains("ABCDABA"));
    }
    
    @Test
    public void testView2() {
        testScan2();
        terminalView.setItems(testTerminal.getItems());
        terminalView.setResult(testTerminal.calculateTotal());
        terminalView.make(); terminalView.print();
        assertTrue(terminalView.getOutput().contains("CCCCCCC"));
    }
        
    @Test
    public void testView3() {
        testScan3();
        terminalView.setItems(testTerminal.getItems());
        terminalView.setResult(testTerminal.calculateTotal());
        terminalView.make(); terminalView.print();
        assertTrue(terminalView.getOutput().contains("ABCD"));
    }

}
