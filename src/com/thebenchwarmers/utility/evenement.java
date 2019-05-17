    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thebenchwarmers.utility;

import java.util.Date;

/**
 *
 */
public class evenement {
     private static int id_courant ;

    public static int getId_courant() {
        return id_courant;
    }

    public static void setId_courant(int id_courant) {
        evenement.id_courant = id_courant;
    }
    
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
   private String lien;

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }
    public String getNomorg() {
        return nomorg;
    }

    public void setNomorg(String nomorg) {
        this.nomorg = nomorg;
    }
    private int idu; 
    private int particiate;

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

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public int getParticiate() {
        return particiate;
    }

    public void setParticiate(int particiate) {
        this.particiate = particiate;
    }

    @Override
    public String toString() {
        return "evenement{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", type=" + type + ", prix=" + prix + ", datedeb=" + datedeb + ", datefin=" + datefin + ", adresse=" + adresse + ", image=" + image + ", nomorg=" + nomorg + ", lien=" + lien + ", idu=" + idu + ", particiate=" + particiate + '}';
    }

 

  

    
    
        
}
