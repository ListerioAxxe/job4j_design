package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator {

    private final int[] numbers;
    private int index = 0;
    private int indEven = validate(index);

    public EvenIt(final int[] numbers) {
        this.numbers = numbers;
    }

    public int validate(int index) {
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
        return indEven != -1;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[indEven++];
    }
}
