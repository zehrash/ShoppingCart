package bg.sofia.uni.fmi.mjt.shopping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bg.sofia.uni.fmi.mjt.shopping.item.Item;

public class MapShoppingCart implements ShoppingCart {

    private Map<Item, Integer> items = new HashMap<>();

    @Override
    public Collection<Item> getUniqueItems() {
        return items.keySet();
    }

    @Override
    public void addItem(Item item) {
        if (item != null) {
            Integer occurrences = items.get(item);
            if (occurrences == null) {
                occurrences = 0;
            }
            items.put(item, ++occurrences);
        }
    }

    @Override
    public void removeItem(Item item) throws ItemNotFoundException {
        if (!items.containsKey(item)) {
            throw new ItemNotFoundException();
        }
        Integer occurrences = items.get(item);
        if (occurrences == 1) {
            items.remove(item);
        } else {
            items.put(item, occurrences - 1);
        }
    }

    @Override
    public double getTotal() {
        double total = 0;
        for (Map.Entry<Item, Integer> e : items.entrySet()) {
            total += e.getKey().getPrice() * e.getValue();
        }
        return total;
    }

    @Override
    public Collection<Item> getSortedItems() {
        List<Item> itemsList = new ArrayList<>(items.keySet());
        itemsList.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return items.get(o1) - items.get(o2);
            }
        });
        return itemsList;
    }
}