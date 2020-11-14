package tracker;

import java.util.List;

public class ShowAction implements UserAction {
    @Override
    public String name() {
        return "Show all items";
    }
    @Override
    public boolean execute(Input input, Store tracker) {
        List<Item> items = tracker.findAll();
        if (items.size() != 0) {
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("Items not found\n");
        }
        return true;
    }
}
