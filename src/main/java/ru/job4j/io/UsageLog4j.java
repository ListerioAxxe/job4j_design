package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Evgen";
        int age = 12;
        boolean validate = true;
        char male = 'M';
        float weight = 75.5f;
        byte iq = 95;
        double experience = 355.55;
        long heigh = 185;
        LOG.debug("User info name : {}, age : {}, validate : {}, male : {}"
                + " weight : {}, iq : {}, experience : {}, heigh : {}", name, age, validate,
                male, weight, iq, experience, heigh);
    }
}