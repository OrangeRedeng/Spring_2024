package com.example.tortland.model;

public enum City {
    NIZHNIY_NOVGOROD("Нижний Новгород"),
    MOSCOW("Москва");

    private final String cityText;

    City(String cityText) {
        this.cityText = cityText;
    }

    public String getCityText() {
        return this.cityText;
    }

}
