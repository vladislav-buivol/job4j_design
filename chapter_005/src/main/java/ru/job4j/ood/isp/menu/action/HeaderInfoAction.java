package ru.job4j.ood.isp.menu.action;

public class HeaderInfoAction implements Action {
    private final String name = ActionTypes.Info.getName();
    private final int id = ActionTypes.Info.getId();

    @Override
    public void execute() {
        System.out.println("Executing...");
        System.out.println("Action Complete!");
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, id: %s", name, id);
    }
}
