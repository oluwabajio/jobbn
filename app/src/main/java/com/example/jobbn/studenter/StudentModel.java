package com.example.jobbn.studenter;

public class StudentModel {

    private String Navn;
    private String kompetanse;

    public StudentModel() {
    }

    public StudentModel(String navn, String kompetanse) {
        this.Navn = navn;
        this.kompetanse = kompetanse;
    }

    public String getNavn() {
        return Navn;
    }

    public void setNavn(String navn) {
        this.Navn = navn;
    }

    public String getKompetanse() {
        return kompetanse;
    }

    public void setKompetanse(String kompetanse) {
        this.kompetanse = kompetanse;
    }
}
