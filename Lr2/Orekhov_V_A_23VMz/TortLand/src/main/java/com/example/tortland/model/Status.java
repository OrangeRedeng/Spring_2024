package com.example.tortland.model;

public enum Status {

    EXPECTATION("Ожидание"),
    COOKING("Готовится"),
    CANCEL("Отменено"),
    DELIVERED("Доставлено");

    private final String statusText;

    Status(String statusText) {
        this.statusText = statusText;
    }

    public String getStatusText() {
        return this.statusText;
    }
}
