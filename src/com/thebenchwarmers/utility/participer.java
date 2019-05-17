/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thebenchwarmers.utility;

import java.util.Date;

/**
 *
 * 
 */
public class participer {
    
    
    
      private int id ; 
    private String nom;
    private String description;
    private String type; 
    private int prix;
//    private java.util.Date datedeb = new java.util.Date();
//    private java.util.Date datefin = new java.util.Date();
//    
    
    public java.util.Date datedeb;
    private java.util.Date datefin;
    private String adresse ;
    private String image ;
    private String nomorg;
    private int ide;
    private int idu; 

    
    
    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Date getDatedeb() {
        return datedeb;
    }

    public void setDatedeb(Date datedeb) {
        this.datedeb = datedeb;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNomorg() {
        return nomorg;
    }

    public void setNomorg(String nomorg) {
        this.nomorg = nomorg;
    }

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }
    
    
    
}
