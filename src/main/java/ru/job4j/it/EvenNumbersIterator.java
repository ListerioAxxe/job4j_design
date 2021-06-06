package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private final int[] numbers;
    private int nextPoint;

    public EvenNumbersIterator(final int[] numbers) {
        this.numbers = numbers;
        nextPoint = nextEl(0);
    }

    public int nextEl(int index) {
        int rsl = -1;
        if (numbers.length != 0) {
            for (int i = index; i < numbers.length; i++) {
                if (numbers[i] % 2 == 0) {
                    rsl = i;
                    break;
                }
            }
        }
        return rsl;
    }

    @Override
    public boolean hasNext() {
        return nextPoint != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer element = numbers[nextPoint++];
        nextPoint = nextEl(nextPoint);
        return element;
    }
}
