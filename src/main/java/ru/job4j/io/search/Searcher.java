package ru.job4j.io.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Searcher {
    private static final Logger LOG = LoggerFactory.getLogger(Searcher.class.getName());

    public static List<Path> search(Predicate<Path> func, Path root) {
        List<Path> result = new ArrayList<>();
        try {
            var searcher = new FileVisitor(func);
            Files.walkFileTree(root, searcher);
            result = searcher.getPaths();
        } catch (IOException e) {
            LOG.error("Search error", e);
        }
        return result;
    }

    public static void write(String target, List<Path> paths) {
        try (var writer = new BufferedWriter(
                new FileWriter(target, Charset.forName("WINDOWS-1251")))) {
            for (Path path : paths) {
                writer.write(path.toAbsolutePath().toString());
                writer.newLine();
            }
        } catch (IOException e) {
            LOG.error("Write error", e);
        }
    }

    public static void main(String[] args) {
        var checkArgs = new CheckArguments(args);
        if (checkArgs.isValid()) {
            var pathToSearchDirectory = Paths.get(checkArgs.directory());
            Predicate<Path> pathPredicate = new SearchPredicate().searchType(
                    checkArgs.typeSearch(),
                    checkArgs.mask());
            List<Path> rsl = search(pathPredicate, pathToSearchDirectory);
            write(checkArgs.writeRslt(), rsl);
        }
    }
}