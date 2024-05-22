package org.seng2050.lab08;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Cart implements Serializable {
    
    private Map<String, Integer> items = new HashMap<>();

    public Cart() {
        System.out.println("New Cart");
    }

    public int addItem(String id, int quantity) {

        System.out.println("Adding %d %s".formatted(quantity, id));

        if (this.items.containsKey(id)) {
            System.out.println("Contains");
            int oldQuantity = this.items.get(id);
            this.items.put(id, oldQuantity+quantity);
            return oldQuantity+quantity;
        } else {
            System.out.println("New Insert");
            this.items.put(id, quantity);
            return quantity;
        }
    }

    public Set<String> getIds() {
        return this.items.keySet();
    }

    public int getQuantity(String id) {
        return this.items.getOrDefault(id, 0);
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    public Set<Map.Entry<String, Integer>> getEntries() {
        return this.items.entrySet();
    }
}
