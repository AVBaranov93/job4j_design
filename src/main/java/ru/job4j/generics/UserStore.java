package ru.job4j.generics;

public class UserStore implements Store<User> {
    private final Store<User> store = new MemStore<>();

    @Override
    public void add(User model) {
        this.store.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return this.store.replace(id, model);
    }

    @Override
    public void delete(String id) {
        this.store.delete(id);
    }

    @Override
    public User findById(String id) {
        return this.store.findById(id);
    }
}
