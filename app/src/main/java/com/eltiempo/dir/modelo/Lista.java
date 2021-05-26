package com.eltiempo.dir.modelo;

import java.util.List;

public class Lista {
    private int dt;
    private Principal main;
    private List<Tiempo> weather;
    private Viento wind;

    public int getDt() {
        return dt;
    }

    public Principal getMain() {
        return main;
    }

    public List<Tiempo> getWeather() {
        return weather;
    }

    public Viento getWind() {
        return wind;
    }
}
