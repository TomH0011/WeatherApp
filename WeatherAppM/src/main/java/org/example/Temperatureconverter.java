package org.example;

public class Temperatureconverter {

    public static long getCelc(double temp) {
        return Math.round((temp * ((double) 9 / 5)) + 32);
    }

    public static long getFaren(double temp) {
        return Math.round((temp - 32) * ((double) 5 / 9));
    }
}
