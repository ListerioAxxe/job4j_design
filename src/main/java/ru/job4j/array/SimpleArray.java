package ru.job4j.array;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private T[] array;
    private int size;

    public SimpleArray(T[] array) {
       this.array = array;
       size = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleArray<?> that = (SimpleArray<?>) o;
        return size == that.size && Arrays.equals(array, that.array);
    }

    @Override
    public String toString() {
        return "SimpleArray{"
                + "array=" + Arrays.toString(array)
                + ", size=" + size
                + '}';
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    public void checkIndex(int index, int size) {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        Objects.checkIndex(index, size);
    }

    public void add(T model) {
        if (size == array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[size++] = model;
    }

    public void set(int index, T model) {
        checkIndex(index, size);
        array[index] = model;
    }

    public void remove(int index) {
        checkIndex(index, size);
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        array[size - 1] = null;
        size--;
    }

    public T get(int index) {
       checkIndex(index, size);
       return (T) array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final Object[] data = array;
            private final int arraySize = size;
            private int point = 0;

            @Override
            public boolean hasNext() {
                return point < arraySize;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) data[point++];
            }
        };
    }
}
