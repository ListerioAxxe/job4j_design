package ru.job4j.garbage.cache;

import java.lang.ref.SoftReference;
import java.nio.file.Path;

public class Emulator {
    public static void main(String[] args) {
        String dir = Path.of("sometimes_dir", "src", "main", "resources").toString();
        DirFileCache dirFileCache = new DirFileCache(dir);
        dirFileCache.get("Finder.txt");
        SoftReference<String> softReference = dirFileCache.cache.get("Finder.txt");
        System.out.println(softReference.get());
    }
}
