package com.example.testing.Models;

public class Produit {
    public int id_prod;
    public String nomProd;
    //public String imageProd;
    public int id_restau;
    public int id_cat;

    public Produit(int id_prod, String nomProd, int id_restau, int id_cat) {
        this.id_prod = id_prod;
        this.nomProd = nomProd;
       // this.imageProd = imageProd;
        this.id_restau = id_restau;
        this.id_cat = id_cat;
    }

    public Produit() {
    }

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

   /* public String getImageProd() {
        return imageProd;
    }
    public void setImageProd(String imageProd) {
        this.imageProd = imageProd;
    }
*/

    public int getId_restau() {
        return id_restau;
    }

    public void setId_restau(int id_restau) {
        this.id_restau = id_restau;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id_prod=" + id_prod +
                ", nomProd='" + nomProd + '\'' +
                ", id_restau=" + id_restau +
                ", id_cat=" + id_cat +
                '}';
    }
}
