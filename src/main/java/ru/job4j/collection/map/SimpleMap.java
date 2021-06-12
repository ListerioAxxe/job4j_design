package ru.job4j.collection.map;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int length = 16;
    private int countEl = 0;
    private int modCount;
    private MapEntry<K, V>[] table;

    public SimpleMap() {
        capacity = (int) (LOAD_FACTOR * length);
        table = new MapEntry[length];
    }

    /**
     * метод вставляет элемент в массив
     */
    @Override
    public boolean put(K key, V value) {
      if (countEl > capacity) {
          expand();
      }
      int index = indexFor(key);
      if (table[index] == null) {
          table[index] = new MapEntry<>(key, value);
          countEl++;
          modCount++;
          return true;
      }
      MapEntry<K, V> elem = table[index];
      if (!elem.getKey().equals(key)) {
          return false;
      }
      elem.setValue(value);
      modCount++;
      return true;
    }

    /**
     * метод рассчитывает индекс бакета
     */

    private int indexFor(K key) {
        int hash = 31;
        hash = hash + key.hashCode();
        return hash % table.length;
    }

    /**
     * метод расширяет массив
     */

    private void expand() {
       MapEntry<K, V>[] newTable = table;
       MapEntry<K, V> element;
       modCount++;
       length = (length * 3) / 2;
       capacity = (int) (length * LOAD_FACTOR);
       table = new MapEntry[length];
       for (var el : newTable) {
           if (el != null) {
               element = el;
               this.put(element.getKey(), element.getValue());
           }
       }
    }

    /**
     * метод возвращает значение по ключу
     */

    @Override
    public V get(K key) {
        int index = indexFor(key);
        if (table[index] == null
           || !(table[index].getKey().equals(key))) {
            return null;
        }
        return table[indexFor(key)].getValue();
    }

    /**
     * метод удаляет элемент по ключу
     */

    @Override
    public boolean remove(K key) {
        int index = indexFor(key);
        if (table[index] == null || !(table[index].getKey().equals(key))) {
            return false;
        }
        table[index] = null;
        modCount++;
        countEl--;
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final MapEntry<K, V>[] tempTable = table;
            private final int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (table[point] != null && point < table.length - 1) {
                    point++;
                }
                return table[point] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return tempTable[point++].getKey();
            }
        };
    }

    private static class MapEntry<K, V> {

        private final K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

}