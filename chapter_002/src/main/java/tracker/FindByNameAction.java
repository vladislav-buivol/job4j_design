package tracker;

import java.util.List;

public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "Find items by name";
    }
    @Override
    public boolean execute(Input input, Store tracker) {
        String name = input.askStr("Enter a name: ");
        List<Item> items = tracker.findByName(name);
        if (items.size() != 0) {
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.printf("Not found item with name: %s\n", name);
        }
        return true;
    }
}
