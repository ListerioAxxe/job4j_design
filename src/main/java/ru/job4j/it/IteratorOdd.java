package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс принимает на вход итератор,
 * и возвращает итератор только по четным элементам
 */

public class IteratorOdd implements Iterator<Integer> {
    private final Iterator<Integer> itr;
    private int cur;

    public IteratorOdd(Iterator<Integer> itr) {
        this.itr = itr;
        cur = 1;
    }

    @Override
    public boolean hasNext() {
        if ((cur & 1) == 0) {
            return true;
        }
        while (itr != null && itr.hasNext()) {
            cur = itr.next();
            if ((cur & 1) == 0) {
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
        int tmp = cur;
        cur = 1;
        return tmp;
    }
}
