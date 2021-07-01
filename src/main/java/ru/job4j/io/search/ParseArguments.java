package ru.job4j.io.search;

import java.util.HashMap;
import java.util.Map;

public class ParseArguments {
    private Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    public static ParseArguments of(String...args) {
        var names = new ParseArguments();
        names.parse(args);
        return names;
    }

    private void parse(String...args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Not correct arguments");
        }
        for (var arg : args) {
            String[] ar = arg.split("=");
            if (ar.length < 2) {
                throw new IllegalArgumentException("Not correct arguments");
            }
            values.put(ar[0], ar[1]);
        }
    }
}
