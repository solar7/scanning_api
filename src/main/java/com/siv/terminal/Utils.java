package com.siv.terminal;

import java.util.List;
import java.util.ArrayList;

public class Utils {
    
    private Utils() {
        //utility class
    }
    
    private interface IPredicate<T> {
        boolean apply(T item);
    }

    private static <T> List<T> filter(List<T> target, IPredicate<T> predicate) {
        List<T> result = new ArrayList<T>();
        for (T element:target) {
            if (predicate.apply(element)) {
                result.add(element);
            }
        }
        return result;
    }
    
    public static List<Item> doFilter(List<Item> items, final String key) {
        return filter(items, new IPredicate<Item>(){
            public boolean apply(Item tempItem) {
                return key.equals(tempItem.getProductCode());
            }
        });
    }

}
