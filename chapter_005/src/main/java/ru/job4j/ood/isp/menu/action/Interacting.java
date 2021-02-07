package ru.job4j.ood.isp.menu.action;

import java.util.List;

public interface Interacting {

    /**
     * add action
     */
    void addAction(Action action);

    /**
     * get available actions
     */
    List<Action> availableActions();
}
