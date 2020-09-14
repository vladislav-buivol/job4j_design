package ru.job4j.generic;


public class RoleStore implements Store<Role> {

    private int nrOfElement = 0;
    private final Store<Role> store = new MemStore<>();

    @Override
    public void add(Role model) {
        nrOfElement++;
        store.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return store.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        nrOfElement--;
        return store.delete(id);
    }

    @Override
    public Role findById(String id) {
        return store.findById(id);
    }

    public int size() {
        return nrOfElement;
    }
}
