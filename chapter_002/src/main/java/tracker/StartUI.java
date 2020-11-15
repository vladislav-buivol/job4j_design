package tracker;

import java.util.*;

public class StartUI {

    public static void createItem(Input input, Store tracker) {
        System.out.println("==== Create a new Item ====");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
    }

    public static void showItems(Store tracker) {
        System.out.println("==== Show all items ====");
        List<Item> items = tracker.findAll();
        if (items.size() != 0) {
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("Items not found\n");
        }
    }

    public static void editItem(Input input, Store tracker) {
        System.out.println("==== Edit item ====");
        String id = input.askStr("Enter the item id: ");
        String name = input.askStr("Enter a new item name: ");
        if (tracker.replace(id, new Item(name))) {
            System.out.printf("Item (id: %s) successfully edited\n", id);
        } else {
            System.out.printf("Item (id: %s) the edit failed\n", id);
        }
    }

    public static void deleteItem(Input input, Store tracker) {
        System.out.println("==== Delete item ====");
        String id = input.askStr("Enter the item id: ");
        if (tracker.delete(id)) {
            System.out.printf("Item (id: %s) successfully deleted\n", id);
        } else {
            System.out.printf("Item (id: %s) deletion failed\n", id);
        }
    }

    public static void findItemById(Input input, Store tracker) {
        System.out.println("==== Find item by Id ====");
        String id = input.askStr("Enter the item id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println(item);
        } else {
            System.out.printf("Not found item with id: %s\n", id);
        }
    }

    public static void findItemByName(Input input, Store tracker) {
        System.out.println("==== Find items by name ====");
        String name = input.askStr("Enter a name: ");
        List<Item> items = tracker.findByName(name);
        if (items != null) {
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.printf("Not found item with name: %s\n", name);
        }
    }

    public void init(Input input, Store tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ", actions.size());
            UserAction action = actions.get(select);
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(List<UserAction> actions) {
        System.out.println("\n---------Menu---------");
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(i + ". " + actions.get(i).name());
        }
        System.out.println("----------------------");
    }

    public static void main(String[] args) {
        /*Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        Store tracker = new SqlTracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction());
        actions.add(new ShowAction());
        actions.add(new EditAction());
        actions.add(new DeleteAction());
        actions.add(new FindByIdAction());
        actions.add(new FindByNameAction());
        actions.add(new ExitAction());
        new StartUI().init(validate, tracker, actions);*/
    }
}
