package ru.job4j.generic;

public class UserStore implements Store<User> {

    private int nrOfElement = 0;
    private final Store<User> store = new MemStore<>();

    @Override
    public void add(User model) {
        nrOfElement++;
        store.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return store.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        nrOfElement--;
        return store.delete(id);
    }

    @Override
    public User findById(String id) {
        return store.findById(id);
    }

    public int size() {
        return nrOfElement;
    }
}