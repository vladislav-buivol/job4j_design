package tracker;

import java.util.Objects;

public class Item implements Comparable<Item> {
    private String id;
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{name: " + this.getName() + ",  id: " + this.getId() + "}";
    }

    @Override
    public int compareTo(Item o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(this.name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}
