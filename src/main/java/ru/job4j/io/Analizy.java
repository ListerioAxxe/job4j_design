package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter write = new PrintWriter(new FileOutputStream(target))) {
            StringBuilder build = new StringBuilder();
            String line;
            while ((line = read.readLine()) != null) {
                String[] rslArray = line.split(" ");
                if (build.length() == 0
                        && (rslArray[0].equals("400") || rslArray[0].equals("500"))) {
                    build.append(rslArray[1]).append("; ");
                } else if (build.length() != 0
                        && (rslArray[0].equals("200") || rslArray[0].equals("300"))) {
                    build.append(rslArray[1]).append("; ");
                    write.println(build);
                    build = new StringBuilder();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(
                new FileOutputStream("./src/main/resources/unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}