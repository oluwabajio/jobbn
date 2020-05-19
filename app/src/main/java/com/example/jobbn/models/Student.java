package com.example.jobbn.models;

public class Student {

    private String navn;
    private String email;
    private String age;
    private String kompetanse;

    public Student() {}

    public Student(String navn, String email, String age, String kompetanse) {
        this.navn = navn;
        this.email = email;
        this.age = age;
        this.kompetanse = kompetanse;
    }

    public String getNavn() {
        return navn;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }

    public String getKompetanse() {
        return kompetanse;
    }
}
