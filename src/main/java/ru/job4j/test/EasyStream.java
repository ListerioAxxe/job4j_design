package ru.job4j.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * EasyStream работает только с типом Integer. В нем есть четыре метода
 * of - получает исходные данные.
 * map - преобразует число в другое число.
 * filter - фильтрует поток элементов.
 * collect - собирает все элементы из source по заданным условиям map и filter.
 * В этом задании нужно использовать шаблон Builder.
 */
public class EasyStream {

    private List<Integer> source;

    public static class StreamBuilder {
        private List<Integer> source;

        public StreamBuilder builds(List<Integer> source) {
            this.source = source;
            return this;
        }

        public EasyStream build() {
            EasyStream easyStream = new EasyStream();
            easyStream.source = source;
            return easyStream;
        }
    }

    public static EasyStream of(List<Integer> source) {
        return new StreamBuilder()
                .builds(source)
                .build();
    }

    public EasyStream map(Function<Integer, Integer> fun) {
        List<Integer> buffer = new ArrayList<>();
        for (int el : source) {
            buffer.add(fun.apply(el));
        }
        return new StreamBuilder()
                .builds(buffer)
                .build();
    }

    public EasyStream filter(Predicate<Integer> fun) {
        List<Integer> buffer = new ArrayList<>();
        for (var el : source) {
            if (fun.test(el)) {
                buffer.add(el);
            }
        }
        return new StreamBuilder()
                .builds(buffer)
                .build();
    }

    public List<Integer> collect() {
        return source;
    }
}