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

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        Predicate<Path> predicate = x -> !x.toFile().getName().endsWith(argsName.get("e"));
        Path dirSource = Path.of(argsName.get("d"));
        Path dirTarget = Path.of(argsName.get("o"));
        List<Path> sources = Search.search(predicate, dirSource);
        Zip zip = new Zip();
        zip.packFiles(sources, dirTarget);
        new Zip().packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}