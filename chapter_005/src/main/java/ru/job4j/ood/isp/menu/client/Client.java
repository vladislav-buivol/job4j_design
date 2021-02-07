package ru.job4j.ood.isp.menu.client;

import ru.job4j.ood.isp.menu.action.Action;
import ru.job4j.ood.isp.menu.action.HeaderInfoAction;
import ru.job4j.ood.isp.menu.element.Header;
import ru.job4j.ood.isp.menu.element.MenuHeader;
import ru.job4j.ood.isp.menu.io.Console;
import ru.job4j.ood.isp.menu.io.InputValidator;
import ru.job4j.ood.isp.menu.io.Validator;
import ru.job4j.ood.isp.menu.menu.Menu;
import ru.job4j.ood.isp.menu.utils.HeaderIdGenerator;

import java.util.List;
import java.util.Scanner;

public class Client implements RunnableClient {
    private final Console console;
    private final Menu menu;

    public Client(Console console, Menu menu) {
        this.console = console;
        this.menu = menu;
    }

    private static Menu prepareHeaders() {
        HeaderIdGenerator generator = new HeaderIdGenerator();
        Header header = new MenuHeader("1. First Chapter", generator);
        Header chapter2 = new MenuHeader("1.2 Next chapter", generator);
        Header chapter3 = new MenuHeader("1.2.3 Next chapter", generator);
        Header chapter4 = new MenuHeader("1.2.3.4 Next chapter", generator);
        Header chapter13 = new MenuHeader("1.3 Next chapter", generator);
        Header newChapter2 = new MenuHeader("2. New chapter", generator);
        header.add(chapter2);
        chapter2.add(chapter3);
        chapter3.add(chapter4);
        header.add(chapter13);
        Menu menu = new Menu(header);
        menu.add(newChapter2);
        return menu;
    }

    public static void main(String[] args) {
        Console console = new Console(new Scanner(System.in));
        Client client = new Client(console, prepareHeaders());
        client.run();
    }

    @Override
    public void run() {
        console.out(menu.prettyPrint());
        loadActions();
        Validator validator = new InputValidator();
        selectHeader(validator);
        selectAction(validator);
    }

    private void selectAction(Validator validator) {
        if (menu.availableActions() != null) {
            console.out(Message.AVAILABLE_ACTIONS.get());
            console.out(menu.availableActions().toString());
            int actionId = Integer.parseInt(askForAction(Message.CHOOSE_ACTION.get(), validator, menu.availableActions()));
            menu.availableActions().get(actionId).execute();
        } else {
            console.out(Message.NO_ACTIONS.get());
        }
    }

    private void selectHeader(Validator validator) {
        String id = askForId(Message.CHOOSE_ITEM.get(), validator);
        while (menu.getById(id) == null) {
            console.out("Header not found. Try again");
            id = askForId(Message.CHOOSE_ITEM.get(), validator);
        }
        console.out(String.format("You selected: %s", menu.getById(id)));
    }

    private void loadActions() {
        menu.addAction(new HeaderInfoAction());
    }

    private String askForAction(String message, Validator validator, List<Action> actions) {
        console.out(message);
        String input = console.read();
        while (!validator.validate(input) || actions.size() < Integer.parseInt(input)) {
            console.out("Incorrect input. Try again");
            input = console.read();
        }
        return input;
    }

    private String askForId(String message, Validator validator) {
        console.out(message);
        return console.read();
    }

    private enum Message {
        CHOOSE_ITEM("Choose menu item by id: "),
        CHOOSE_ACTION("Choose your action: "),
        AVAILABLE_ACTIONS("Available actions: "),
        NO_ACTIONS("No available actions");
        String messageText;

        public String get() {
            return messageText;
        }

        Message(String messageText) {
            this.messageText = messageText;
        }
    }
}
