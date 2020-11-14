package tracker;

public class EditAction implements UserAction {
    @Override
    public String name() {
        return "Edit item";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        String id = input.askStr("Enter the item id: ");
        String name = input.askStr("Enter a new item name: ");
        if (tracker.replace(id, new Item(name))) {
            System.out.printf("Item (id: %s) successfully edited\n", id);
        } else {
            System.out.printf("Item (id: %s) the edit failed\n", id);
        }
        return true;
    }
}
