package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (var read = new BufferedReader(new FileReader(source));
             var write = new PrintWriter(new FileOutputStream(target))) {
            var build = new StringBuilder();
            String line;
            while ((line = read.readLine()) != null) {
                String[] text = line.split(" ");
                if (build.length() == 0 && (text[0].equals("400") || text[0].equals("500"))) {
                    build.append(text[1]).append(";");
                } else if (build.length() != 0
                        && (text[0].equals("200") || text[0].equals("400"))) {
                    build.append(text[1]).append(";");
                    write.println(build);
                    build = new StringBuilder();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}