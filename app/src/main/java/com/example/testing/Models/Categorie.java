package com.example.testing.Models;

public class Categorie {
    public int id_cat;
    public String nomCat;
    public String imageCat;
    public int id_restau;

    public Categorie(int id_cat, String nomCat, String imageCat, int id_restau) {
        this.id_cat = id_cat;
        this.nomCat = nomCat;
        this.imageCat = imageCat;
        this.id_restau = id_restau;
    }

    public Categorie() {
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public String getImageCat() {
        return imageCat;
    }

    public void setImageCat(String imageCat) {
        this.imageCat = imageCat;
    }

    public int getId_restau() {
        return id_restau;
    }

    public void setId_restau(int id_restau) {
        this.id_restau = id_restau;
    }
}
