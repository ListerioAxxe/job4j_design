package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.get("name"), is("Petr Arsentev"));
        assertThat(config.get("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.get("name"), is("Petr Arsentev"));
        assertThat(config.get("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairEmpty() {
        String path = "./data/pair_with_null.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.get("name"));
    }
}