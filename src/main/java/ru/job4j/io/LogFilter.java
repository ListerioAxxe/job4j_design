package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> rslList = new ArrayList<>();
        try (var in = new BufferedReader(new FileReader(file))) {
           rslList = in.lines()
                   .filter(x -> x.contains("404"))
                   .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rslList;
    }

    public static void save(List<String> log, String file) {
       try (PrintWriter out = new PrintWriter(
               new BufferedOutputStream(new FileOutputStream(file)))) {
           for (var el : log) {
               out.write(el + System.lineSeparator());
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
        save(log, "404.txt");
    }
}