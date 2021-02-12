package ru.job4j.ood.dip;

public class SimpleOrderService implements OrderService {

    private OrderStore orderStore;

    public SimpleOrderService(OrderStore orderStore) {
        this.orderStore = orderStore;
    }
}
