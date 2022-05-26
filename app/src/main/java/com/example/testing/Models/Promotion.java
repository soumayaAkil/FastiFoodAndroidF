package com.example.testing.Models;

public class Promotion {
    public int id_promo;
    public String photo_promo;
    public double prix_promo;
    public String designiation;
    public int id_restau;

    public Promotion(int id_promo, String photo_promo, double prix_promo, String designiation, int id_restau) {
        this.id_promo = id_promo;
        this.photo_promo = photo_promo;
        this.prix_promo = prix_promo;
        this.designiation = designiation;
        this.id_restau = id_restau;
    }

    public int getId_promo() {
        return id_promo;
    }

    public void setId_promo(int id_promo) {
        this.id_promo = id_promo;
    }

    public String getPhoto_promo() {
        return photo_promo;
    }

    public void setPhoto_promo(String photo_promo) {
        this.photo_promo = photo_promo;
    }

    public double getPrix_promo() {
        return prix_promo;
    }

    public void setPrix_promo(double prix_promo) {
        this.prix_promo = prix_promo;
    }

    public String getDesigniation() {
        return designiation;
    }

    public void setDesigniation(String designiation) {
        this.designiation = designiation;
    }

    public int getId_restau() {
        return id_restau;
    }

    public void setId_restau(int id_restau) {
        this.id_restau = id_restau;
    }
}
