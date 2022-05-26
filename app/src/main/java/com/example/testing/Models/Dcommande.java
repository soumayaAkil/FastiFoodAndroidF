package com.example.testing.Models;

public class Dcommande {
    public int id_detailCom;
    public int quantite;
    public  String produit;
    public  String unite;
    public String image_produit;
    public  double prixProd;

    public Dcommande() {
    }

    public Dcommande(int id_detailCom, int quantite, String produit, String unite, String image_produit, double prixProd) {
        this.id_detailCom = id_detailCom;
        this.quantite = quantite;
        this.produit = produit;
        this.unite = unite;
        this.image_produit = image_produit;
        this.prixProd = prixProd;
    }

    public int getId_detailCom() {
        return id_detailCom;
    }

    public void setId_detailCom(int id_detailCom) {
        this.id_detailCom = id_detailCom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getImage_produit() {
        return image_produit;
    }

    public void setImage_produit(String image_produit) {
        this.image_produit = image_produit;
    }

    public double getPrix() {
        return prixProd;
    }

    public void setPrix(double prix) {
        this.prixProd = prix;
    }
}
