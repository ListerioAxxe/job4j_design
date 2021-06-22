package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {
       private int modCount = 0;
       private int size = 0;
       private Node<E> first;
       private Node<E> last;

    public void checkIndex(int index) {
        Objects.checkIndex(index, size);
    }

    @Override
    public void add(E value) {
        Node<E> l = last;
        Node<E> newNod = new Node<>(l, value, null);
        last = newNod;
        if (l == null || size == 0) {
            first = newNod;
        } else {
            l.next = newNod;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        Node<E> rsl = first;
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> point = first;
            private final int mod = modCount;

            @Override
            public boolean hasNext() {
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = point.item;
                point = point.next;
                return rsl;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
