package ru.job4j.test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Odd implements Iterator<Integer> {
    private final Iterator<Integer> it;
    private int curr;

    public Odd(Iterator<Integer> it) {
        this.it = it;
        curr = 1;
    }

    @Override
    public boolean hasNext() {
        if ((curr % 2) == 0) {
            return true;
        }
        while (it != null && it.hasNext()) {
            curr = it.next();
            if ((curr % 2) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int tmp = curr;
        curr = 1;
        return tmp;
    }
}
