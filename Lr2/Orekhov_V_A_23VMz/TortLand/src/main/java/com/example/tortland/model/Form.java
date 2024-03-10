package com.example.tortland.model;

public enum Form {

    CLASSIC("Классический"),
    FESTIVE("Праздничный");
    private final String formText;

    Form(String formText) {
        this.formText = formText;
    }

    public String getFormText() {
        return this.formText;
    }
}
