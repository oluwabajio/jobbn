package com.example.jobbn.models;

public class Bedrifter {
    private String navn;
    private String email;

    public Bedrifter() {
    }

    public Bedrifter(String navn, String email) {
        this.navn = navn;
        this.email = email;
    }

    public String getNavn() {
        return navn;
    }

    public String getEmail() {
        return email;
    }
}
