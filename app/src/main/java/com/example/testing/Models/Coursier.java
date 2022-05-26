package com.example.testing.Models;

public class Coursier {
    public int id_fact;
    public int id_coursier;
    public String status;
    public String nomCoursier;
    public String prenomCoursier;
    public String numCoursier;
    public String imageCoursier;

    public Coursier(int id_fact, int id_coursier, String status, String nomCoursier, String prenomCoursier, String numCoursier, String imageCoursier) {
        this.id_fact = id_fact;
        this.id_coursier = id_coursier;
        this.status = status;
        this.nomCoursier = nomCoursier;
        this.prenomCoursier = prenomCoursier;
        this.numCoursier = numCoursier;
        this.imageCoursier = imageCoursier;
    }

    public int getId_fact() {
        return id_fact;
    }

    public void setId_fact(int id_fact) {
        this.id_fact = id_fact;
    }

    public int getId_coursier() {
        return id_coursier;
    }

    public void setId_coursier(int id_coursier) {
        this.id_coursier = id_coursier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNomCoursier() {
        return nomCoursier;
    }

    public void setNomCoursier(String nomCoursier) {
        this.nomCoursier = nomCoursier;
    }

    public String getPrenomCoursier() {
        return prenomCoursier;
    }

    public void setPrenomCoursier(String prenomCoursier) {
        this.prenomCoursier = prenomCoursier;
    }

    public String getNumCoursier() {
        return numCoursier;
    }

    public void setNumCoursier(String numCoursier) {
        this.numCoursier = numCoursier;
    }

    public String getImageCoursier() {
        return imageCoursier;
    }

    public void setImageCoursier(String imageCoursier) {
        this.imageCoursier = imageCoursier;
    }
}
