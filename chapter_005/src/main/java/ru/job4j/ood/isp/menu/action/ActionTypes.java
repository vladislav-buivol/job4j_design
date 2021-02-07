package ru.job4j.ood.isp.menu.action;

public enum ActionTypes {
    Info("Info", 0);

    private String name;
    private int id;

    ActionTypes(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ActionTypes{" + "name='" + name + '\'' + ", id=" + id + '}';
    }
}
