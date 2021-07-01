package ru.job4j.io.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SearchPredicate {
    private static final Logger LOG = LoggerFactory.getLogger(SearchPredicate.class.getName());

    public Predicate<Path> searchType(String searchType, String fileSearchArg) {
        Predicate<Path> rsl = null;
        try {
            if (searchType.equals("name")) {
                rsl = foundNames(fileSearchArg);
            }
            if (searchType.equals("regex")) {
                rsl = foundRegex(fileSearchArg);
            }
            if (searchType.equals("mask")) {
                rsl = foundRegex(maskToRegEx(fileSearchArg));
            }
        } catch (IllegalArgumentException e) {
            LOG.error("Search type error", e);
        }
        return rsl;
    }

   private Predicate<Path> foundNames(String file) {
       return found -> found.toFile().getName().equals(file);
   }

    private Predicate<Path> foundRegex(String file) {
        return foundReg -> {
            var pattern = Pattern.compile(file);
            var matcher = pattern.matcher(foundReg.toFile().getName());
            return matcher.find();
        };
    }

    private String maskToRegEx(String mask) {
        var builder = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            var symb = mask.charAt(i);
            if (symb == '*') {
                builder.append(".*");
            } else if (symb == '.') {
                builder.append("\\.");
            } else {
                builder.append(symb);
            }
        }
        return builder.toString();
    }

}
