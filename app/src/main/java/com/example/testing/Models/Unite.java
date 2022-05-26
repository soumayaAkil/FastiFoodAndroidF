package com.example.testing.Models;

public class Unite {
    public int id_unite;
    public String nomUnite;
    public int id_cat;

    public Unite(int id_unite, String nomUnite, int id_cat) {
        this.id_unite = id_unite;
        this.nomUnite = nomUnite;
        this.id_cat = id_cat;
    }

    public Unite() {
    }

    public int getId_unite() {
        return id_unite;
    }

    public void setId_unite(int id_unite) {
        this.id_unite = id_unite;
    }

    public String getNomUnite() {
        return nomUnite;
    }

    public void setNomUnite(String nomUnite) {
        this.nomUnite = nomUnite;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }
}
