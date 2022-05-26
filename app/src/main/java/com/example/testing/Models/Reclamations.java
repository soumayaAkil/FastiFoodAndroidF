package com.example.testing.Models;

public class Reclamations {
    public int id_reclamation;
    public String Description_Reclamation;
    public String Type_reclamation;
    public String nom_client;
    public String Prenom_client;
    public String Adresse_client;
    public String somme_commande;
    public int   quantite_produit;
    public String nomProd;
    public String unite_produit;
    public int id_TypeReclamation;

    public int getId_TypeReclamation() {
        return id_TypeReclamation;
    }

    public void setId_TypeReclamation(int id_TypeReclamation) {
        this.id_TypeReclamation = id_TypeReclamation;
    }

    public Reclamations() {
    }

    public Reclamations(int id_reclamation, String description_Reclamation, String type_Reclamation, String nom_client, String prenom_client, String adresse_client, String somme_commande, int quantite_produit, String nomProd, String unite_produit) {
        this.id_reclamation = id_reclamation;
        Description_Reclamation = description_Reclamation;
        Type_reclamation = type_Reclamation;
        this.nom_client = nom_client;
        Prenom_client = prenom_client;
        Adresse_client = adresse_client;
        this.somme_commande = somme_commande;
        this.quantite_produit = quantite_produit;
        this.nomProd = nomProd;
        this.unite_produit = unite_produit;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public String getDescription_Reclamation() {
        return Description_Reclamation;
    }

    public void setDescription_Reclamation(String description_Reclamation) {
        Description_Reclamation = description_Reclamation;
    }

    public String getType_Reclamation() {
        return Type_reclamation;
    }

    public void setType_Reclamation(String type_Reclamation) {
        Type_reclamation = type_Reclamation;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public String getPrenom_client() {
        return Prenom_client;
    }

    public void setPrenom_client(String prenom_client) {
        Prenom_client = prenom_client;
    }

    public String getAdresse_client() {
        return Adresse_client;
    }

    public void setAdresse_client(String adresse_client) {
        Adresse_client = adresse_client;
    }

    public String getSomme_commande() {
        return somme_commande;
    }

    public void setSomme_commande(String somme_commande) {
        this.somme_commande = somme_commande;
    }

    public int getQuantite_produit() {
        return quantite_produit;
    }

    public void setQuantite_produit(int quantite_produit) {
        this.quantite_produit = quantite_produit;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public String getUnite_produit() {
        return unite_produit;
    }

    public void setUnite_produit(String unite_produit) {
        this.unite_produit = unite_produit;
    }
}
