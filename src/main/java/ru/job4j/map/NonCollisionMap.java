package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                int index = indexFor(hash(Objects.hashCode(entry.key)));
                newTable[index] = entry;
            }
        }
        table = newTable;
    }

    private int index(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private boolean compareKeys(K key1, K key2) {
        return Objects.hashCode(key1) == Objects.hashCode(key2)
                && Objects.equals(key1, key2);
    }

    @Override
    public boolean put(K key, V value) {
        if (count >= LOAD_FACTOR * capacity) {
            expand();
        }
        int index = index(key);
        boolean isEmpty = table[index] == null;
        if (isEmpty) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return isEmpty;
    }

    @Override
    public V get(K key) {
        int index = index(key);
        boolean getValue = table[index] != null;
        MapEntry<K, V> rsl = table[index];
        if (rsl != null) {
            getValue = compareKeys(rsl.key, key);
        }
        return getValue ? rsl.value : null;
    }

    @Override
    public boolean remove(K key) {
        int index = index(key);
        boolean removed = table[index] != null;
        if (removed) {
            if (compareKeys(table[index].key, key)) {
                table[index] = null;
                modCount++;
            } else {
                removed = false;
            }
        }
        return removed;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int index;
            private final int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;
        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        NonCollisionMap<Integer, Integer> map = new NonCollisionMap<>();
        System.out.println(map.hash(0));
        System.out.println(map.hash(65535));
        System.out.println(map.hash(65536));
        System.out.println(map.indexFor(0));
        System.out.println(map.indexFor(7));
        System.out.println(map.indexFor(8));
        System.out.println(map.indexFor(map.hash(8)));
        /*NonCollisionMap<Integer, String> map = new NonCollisionMap<>();
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(4, "4");
        map.put(null, "0000");
        map.put(15, "15");
        map.put(8, "8");*/

    }
}
