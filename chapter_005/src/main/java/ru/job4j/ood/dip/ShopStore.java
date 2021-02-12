package ru.job4j.ood.dip;


import java.util.Set;

public interface ShopStore {

    boolean saveUser(User user);

    boolean saveOrder(User user, Order order);

    Set<Order> getOrders(User user);

    Set<User> getUsers();

}
