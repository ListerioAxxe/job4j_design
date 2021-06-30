package ru.job4j.io.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "adres")
public class Adres {

    @XmlAttribute
    private int build;
    private int number;
    private String street;

    public Adres() {
    }

    public Adres(int build, int number, String street) {
        this.build = build;
        this.number = number;
        this.street = street;
    }

    public int getBuild() {
        return build;
    }

    public int getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
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
