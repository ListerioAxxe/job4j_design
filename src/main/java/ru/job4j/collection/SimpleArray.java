package ru.job4j.collection;

import java.util.*;
/**
 * Внутри контейнер должен базироваться на массиве Object[] container.
 * Количество изменений - это показатель, который нам говорит, сколько
 * раз коллекция была изменена с момента ее создания
 */

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int size = 0;
    private int modCount = 0;

    public SimpleArray() {
        this.container = new Object[10];
    }

    /**
     * Когда элементов становится больше чем ячеек в массиве ArrayList создает
     * новый массив с большим размером.Контейнер должен быть динамическим, т.е. при
     * полном заполнении увеличиваться
     */

    public void checkLength(int newLength) {
        int length = container.length;
        if (newLength == length) {
            int changeLength = (length * 3 / 2);
            container = Arrays.copyOf(container, changeLength);
        }
    }

    /**
     * В методах, где используется индекс нужно делать валидацию.
     * Индекс должен находиться в рамках добавленных элементов
     */

    public void chekIndex(int index, int size) {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        Objects.checkIndex(index, size);
    }

    /**
     * Количество элементов - это показатель, который нам говорит, сколько элементов
     * в коллекции на данный момент. Переменную, которая будет отвечать за это число можно
     * применять для размещения элементов (если говорить про динамический массив).
     */

    public void add(T model) {
        checkLength(size + 1);
        container[size++] = model;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final Object[] data = container;
            private final int arraySize = size;
            private int point = 0;
            private int count = modCount;

            /** Итератор должен реализовывать fail-fast поведение, т.е. если
             * с момента создания итератора в коллекцию добавили новый элемент,
             * итератор должен кидать ConcurrentModificationException.
             * ConcurrentModificationException. Относится ко второму показателю.
             * Чтобы кинуть это исключение заводят отдельную переменную в итераторе
             * expectedModCount = modCount и проверяют условие if (expectedModCount != modCount)
             */

            @Override
            public boolean hasNext() {
                if (count != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < arraySize;
            }

            /**  NoSuchElementException. Относиться к первому показателю. Тут просто.
             * Если итератор "уперься", т.е. нет больше элементов, а клиент вызвал этот метод,
             * то этим исключение мы ему подчеркиваем, что элементов больше нет.
             */

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