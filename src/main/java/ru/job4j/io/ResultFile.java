package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {

    public static void main(String[] args) {
            try (FileOutputStream fil = new FileOutputStream("result.txt")) {
                for (int i = 1; i < 11; i++) {
                    for (int j = 1; j < 11; j++) {
                        fil.write((i * j + " ").getBytes());
                    }
                    fil.write(System.lineSeparator().getBytes());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }