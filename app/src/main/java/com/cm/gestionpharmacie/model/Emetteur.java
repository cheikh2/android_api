package com.cm.gestionpharmacie.model;

public class Emetteur {
    private Integer id;
    private String nomEme;
    private String prenomEme;
    private String telephoneEme;
    private String cni;

    public Emetteur() {
    }

    public Emetteur(Integer id, String nomEme, String prenomEme, String telephoneEme, String cni) {
        this.id = id;
        this.nomEme = nomEme;
        this.prenomEme = prenomEme;
        this.telephoneEme = telephoneEme;
        this.cni = cni;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomEme() {
        return nomEme;
    }

    public void setNomEme(String nomEme) {
        this.nomEme = nomEme;
    }

    public String getPrenomEme() {
        return prenomEme;
    }

    public void setPrenomEme(String prenomEme) {
        this.prenomEme = prenomEme;
    }

    public String getTelephoneEme() {
        return telephoneEme;
    }

    public void setTelephoneEme(String telephoneEme) {
        this.telephoneEme = telephoneEme;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }
}
