package com.eltiempo.dir.modelo;

public class Principal {
    private Double temp;
    private String feels_like;
    private Double temp_min;
    private Double temp_max;
    private Double pressure;
    private Double humidity;

    public double getTemp() {
        return temp;
    }

    public String getFeels_like() {
        return feels_like;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }
}
