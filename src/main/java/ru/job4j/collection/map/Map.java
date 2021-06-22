package ru.job4j.collection.map;

import java.util.Objects;

public interface Map<K, V> extends Iterable<K> {

    boolean put(K key, V value);

    V get(K key);

    boolean remove(K key);
}

class Adress {
    private final String street;
    private final int build;

    public Adress(String street, int build) {
        this.street = street;
        this.build = build;
    }

}
