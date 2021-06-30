package ru.job4j.io.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "citizen")
@XmlAccessorType(XmlAccessType.FIELD)
public class Citizen {

    @XmlAttribute
    private static long serialVersionUID = 1L;
    private Adres adres;
    private boolean covid;
    private int age;

    @XmlElementWrapper(name = "info")
    @XmlElement(name = "information")
    private String[] info;

    public Citizen() {
    }

    public Citizen(boolean covid, Adres adres, int age, String...info) {
        this.covid = covid;
        this.adres = adres;
        this.age = age;
        this.info = info;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Adres getAdres() {
        return adres;
    }

    public boolean isCovid() {
        return covid;
    }

    public int getAge() {
        return age;
    }

    public String[] getInfo() {
        return info;
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

    public static void main(String[] args) throws Exception {

        /**
         *  JSONObject из json-строки строки
         *  */

        JSONObject jsonAdres = new JSONObject("{"
                + "\"adres\":"
                + "{"
                + "\"build\":\"35\","
                + "\"number\":\"51\","
                + "\"street\":\"voroshilova\""
                + "},"
                + "}");

        /**
         *  JSONArray из ArrayList
         */
        List<String> list = new ArrayList<>();
        list.add("Poproshaika");
        list.add("Holost");
        JSONArray jsonInfo = new JSONArray(list);

        Citizen citizen = new Citizen(
                true, new Adres(35, 51, "Voroshilova"), 27, "Poproshaika", "Holost");

        /**
         *  JSONObject напрямую методом put
         */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", citizen.isCovid());
        jsonObject.put("adres", jsonAdres);
        jsonObject.put("age", citizen.getAge());
        jsonObject.put("info", jsonInfo);

        /**
        *  результат
        */
        System.out.println(jsonObject.toString());

        /**
        *  обьект citizen в json строку
        */
        System.out.println(new JSONObject(citizen).toString());
    }
}
