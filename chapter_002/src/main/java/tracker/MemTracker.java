package tracker;

import java.util.*;

public class MemTracker {
    private final List<Item> items = new ArrayList<>();


    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    private String generateId() {
        Random rnd = new Random();
        return String.valueOf(rnd.nextLong() + System.currentTimeMillis());
    }

    private int indexOf(String id) {
        int rsl = -1;
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId().equals(id)) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    public Item findById(String id) {
        int index = indexOf(id);
        return index != -1 ? this.items.get(index) : null;
    }

    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        int index = 0;
        for (Item item : this.items) {
            if (key.equals(item.getName())) {
                items.add(index, item);
                index++;
            }
        }
        return items;
    }

    public List<Item> findAll() {
        return this.items;
    }

    public boolean replace(String id, Item item) {
        boolean result = false;
        int index = indexOf(id);
        if (index != -1) {
            item.setId(id);
            this.items.add(index, item);
            result = true;
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        int index = indexOf(id);
        if (index != -1) {
            this.items.remove(index);
            result = true;
        }
        return result;
    }
}
