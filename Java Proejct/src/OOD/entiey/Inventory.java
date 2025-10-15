package OOD.entiey;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<Item, Integer> stock = new HashMap<>();
    
    public void addItem(Item item, int quantity) {
        stock.put(item, stock.getOrDefault(item, 0) + quantity);
    }

    public boolean hasItem(Item item) {
        return stock.getOrDefault(item, 0) > 0;
    }

    public void dispenseItem(Item item) { // 分发 item
        if(!hasItem(item)) throw new RuntimeException("Out of Stock");
        stock.put(item, stock.get(item) - 1);
    }
} 
