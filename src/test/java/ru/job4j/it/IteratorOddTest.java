package ru.job4j.it;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class IteratorOddTest  {

    Iterator<Integer> l = new IteratorOdd(List.of(1, 4, 5, 6, 7).iterator());

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(l.hasNext(), is(true));
        assertThat(l.hasNext(), is(true));
        assertThat(l.next(), is(4));
        assertThat(l.next(), is(6));
    }

}