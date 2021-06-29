package ru.job4j.io.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Citizen {
    private static final long serialVersionUID = 1L;
    private final Adres adres;
    private final boolean covid;
    private final int age;
    private final String[] info;

    public Citizen(boolean covid, Adres adres, int age, String...info) {
        this.covid = covid;
        this.adres = adres;
        this.age = age;
        this.info = info;
    }

    @Override
    public String toString() {
        return "Citizen{"
                + "adres=" + adres
                + ", covid=" + covid
                + ", age=" + age
                + ", info=" + Arrays.toString(info)
                + '}';
    }

    public static void main(String[] args) {
        final Citizen citizen = new Citizen(
                true, new Adres(35, 51, "Voroshilova"), 27, "Poproshaika", "Holost");

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(citizen));

        final String citizenJson =
                "{"
                   + "\"covid\":\"true\","
                   + "\"adres\":"
                   + "{"
                        + "\"build\":\"35\","
                        + "\"number\":\"51\","
                        + "\"street\":\"voroshilova\""
                        + "},"
                   + "\"age\":\"27\","
                   + "\"info\":"
                   + "[\"Poproshaika\",\"Holost\"]"
                + "}";

        final Citizen citizenModificated = gson.fromJson(citizenJson, Citizen.class);
        System.out.println(citizenModificated);
    }

}
