package tracker;

public class FindByIdAction implements UserAction {
    @Override
    public String name() {
        return "Find item by Id";
    }
    @Override
    public boolean execute(Input input, Store tracker) {
        String id = input.askStr("Enter the item id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println(item);
        } else {
            System.out.printf("Not found item with id: %s\n", id);
        }
        return true;
    }
}
