/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thebenchwarmers.utility;

/**
 *
 */
public class notif {
    
    private int id;
    private int ide;
    private int idu;
    private String nominviteur;
    private String nome;
    private String imagee;
    private String adressee;

    public int getId() {
        return id;
    }

 

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public String getNominviteur() {
        return nominviteur;
    }

    public void setNominviteur(String nominviteur) {
        this.nominviteur = nominviteur;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagee() {
        return imagee;
    }

    public void setImagee(String imagee) {
        this.imagee = imagee;
    }

    public String getAdressee() {
        return adressee;
    }

    public void setAdressee(String adressee) {
        this.adressee = adressee;
    }

    public notif(int id, int ide, int idu, String nominviteur, String nome, String imagee, String adressee) {
        this.id = id;
        this.ide = ide;
        this.idu = idu;
        this.nominviteur = nominviteur;
        this.nome = nome;
        this.imagee = imagee;
        this.adressee = adressee;
    }

    public notif() {
    }

    @Override
    public String toString() {
        return "notif{" + "id=" + id + ", ide=" + ide + ", idu=" + idu + ", nominviteur=" + nominviteur + ", nome=" + nome + ", imagee=" + imagee + ", adressee=" + adressee + '}';
    }
    
}
