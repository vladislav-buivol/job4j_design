package tracker;

import java.util.Comparator;

public class ItemCompare implements Comparator<Item> {
    private boolean descending;

    public ItemCompare() {
        this(false);
    }

    public ItemCompare(boolean descending) {
        this.descending = descending;
    }

    @Override
    public int compare(Item a, Item b) {
        return (descending)
                ? b.getName().compareTo(a.getName())
                : a.getName().compareTo(b.getName());
    }
}
