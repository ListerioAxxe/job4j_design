package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            var rsl = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                    rsl.append((char) read);
            }
            String[] arr = rsl.toString().split(System.lineSeparator());
            for (var el : arr) {
                System.out.println(el + " "
                        + (Integer.parseInt(el) % 2 == 0 ? "Четное" : "Нечетное"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
