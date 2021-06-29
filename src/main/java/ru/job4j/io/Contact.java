package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {
    private static final long SERIAL_VERSION = 1L;
    private int code;
    private String number;

    public Contact(int code, String number) {
        this.code = code;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "code=" + code
                + ", number='" + number + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123, "8 965 777 77 77");

        File tempFile = Files.createTempFile(null, null).toFile();
        try (var written = new ObjectOutputStream(new FileOutputStream(tempFile))) {
              written.writeObject(contact);
        }

        try (var input = new ObjectInputStream(new FileInputStream(tempFile))) {
            final Contact contactFromFile = (Contact) input.readObject();
            System.out.println(contactFromFile);
        }
    }
}
