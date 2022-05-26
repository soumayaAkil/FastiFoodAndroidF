package com.example.testing.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MyCart")
public class Cart {




    @PrimaryKey
    @ColumnInfo(name = "id_prod")
    public int id_prod;

    @ColumnInfo(name = "nomProd")
    public String nomProd;

    @ColumnInfo(name = "imageProd")
    public String imageProd;

    @ColumnInfo(name = "nomRest")
    public String nomRest;

    @ColumnInfo(name = "prixProd")
    public String prixProd;

    @ColumnInfo(name = "quantite")
    public int quantite ;

    @ColumnInfo(name = "price")
    public String price;


    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public String getImageProd() {
        return imageProd;
    }

    public void setImageProd(String imageProd) {
        this.imageProd = imageProd;
    }

    public String getNomRest() {
        return nomRest;
    }

    public void setNomRest(String nomRest) {
        this.nomRest = nomRest;
    }

    public String getPrixProd() {
        return prixProd;
    }

    public void setPrixProd(String prixProd) {
        this.prixProd = prixProd;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
