package ru.job4j.test;

import org.junit.Test;
import ru.job4j.it.IteratorOdd;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OddTest {

    Iterator<Integer> l = new Odd(List.of(1, 4, 5, 6, 7).iterator());

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(l.hasNext(), is(true));
        assertThat(l.hasNext(), is(true));
        assertThat(l.next(), is(4));
        assertThat(l.next(), is(6));
    }

}