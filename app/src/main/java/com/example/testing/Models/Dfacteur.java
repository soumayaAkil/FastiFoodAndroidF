package com.example.testing.Models;


public class Dfacteur {

    public int id_com;
    public double  somme_com;
    public  String reponse;
    public String nomRestau;
    public String logo;
    public  int id_fact;


    public Dfacteur(int id_com, double somme_Dfacteur, String reponse, String nomRestau, String logo, int id_fact) {
        this.id_com = id_com;
        this.nomRestau = nomRestau;
        this.reponse = reponse;
        this.somme_com = somme_Dfacteur;
        this.logo = logo;
        this.id_fact = id_fact;
    }

    public int getId_com() {
        return id_com;
    }

    public void setId_com(int id_com) {
        this.id_com = id_com;
    }

    public String getNomRestau() {
        return nomRestau;
    }

    public void setNomRestau(String nomRestau) {
        this.nomRestau = nomRestau;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public double getSomme_Dfacteur() {
        return somme_com;
    }

    public void setSomme_Dfacteur(double somme_Dfacteur) {
        this.somme_com = somme_Dfacteur;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getId_fact() {
        return id_fact;
    }

    public void setId_fact(int id_fact) {
        this.id_fact = id_fact;
    }
}
