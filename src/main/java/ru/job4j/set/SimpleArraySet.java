package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArraySet<T> implements SimpleSet<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>(0);
    private int modCount;
    @Override
    public boolean add(T value) {
        boolean isAdded = true;
        for (int i = 0; i < set.size(); i++) {
            if (Objects.equals(value, set.get(i))) {
                isAdded = false;
                break;
            }
        }
        if (isAdded) {
            set.add(value);
            modCount++;
        }
        return isAdded;
    }

    @Override
    public boolean contains(T value) {
        boolean contains = false;
        for (int i = 0; i < set.size(); i++) {
            if (Objects.equals(value, set.get(i))) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index;
            private final int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < set.size();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return set.get(index++);
            }
        };
    }
}
