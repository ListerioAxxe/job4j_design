package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        try (var zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target.toString())))) {
            for (var el : sources) {
                zip.putNextEntry(new ZipEntry(el.toString()));
                try (var out = new BufferedInputStream(new FileInputStream(el.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        Path directorySource = Path.of(argsName.get("d"));
        Predicate<Path> pr = x -> !x.toFile().getName().endsWith(argsName.get("e"));
        Path directoryTarget = Path.of(argsName.get("o"));
        List<Path> sources = null;
        try {
            sources = Search.search(pr, directorySource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Zip zip = new Zip();
        zip.packFiles(sources, directoryTarget);
        new Zip().packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}