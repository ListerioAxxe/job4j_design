package ru.job4j.io.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CheckArguments {

    private static final Logger LOG = LoggerFactory.getLogger(CheckArguments.class.getName());
    private ParseArguments parseArguments;

    public CheckArguments(String...args) {
        parseArguments = ParseArguments.of(args);
    }

    public String directory() {
        return parseArguments.get("d");
    }

    public String mask() {
        return parseArguments.get("n");
    }

    public String typeSearch() {
        return parseArguments.get("t");
    }

    public String writeRslt() {
        return parseArguments.get("o");
    }

    public boolean isValid() {
        try {
            directory();
            mask();
            typeSearch();
            writeRslt();
        } catch (IllegalArgumentException e) {
            LOG.error("Not valid args", e);
        }
        return true;
    }
}
