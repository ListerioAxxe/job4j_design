package ru.job4j.io.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

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

        Citizen citizen = new Citizen(
                true, new Adres(35, 51, "Voroshilova"), 27, "Poproshaika", "Holost");
        /**
         * сериализуем обьект в XML
         * Получаем контекст для доступа к АПИ
         * Создаем сериализатор
         * Указываем, что нам нужно форматирование
         * Сериализуем
         */
        JAXBContext context = JAXBContext.newInstance(Citizen.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(citizen, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        /**
        * Для десериализации нам нужно создать десериализатор
        * // десериализуем
        */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Citizen result = (Citizen) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}
