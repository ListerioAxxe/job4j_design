package ru.job4j.io.json;

public class Adres {
    private final int build;
    private final int number;
    private final String street;

    public Adres(int build, int number, String street) {
        this.build = build;
        this.number = number;
        this.street = street;
    }

    @Override
    public String toString() {
        return "Adres{"
                + "build=" + build
                + ", number=" + number
                + ", street='" + street + '\''
                + '}';
    }
}
