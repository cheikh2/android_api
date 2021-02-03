package com.cm.gestionpharmacie.model;

public class Recepteur {
    private Integer id;
    private String nomRec;
    private String prenomRec;
    private String telephoneRec;

    public Recepteur() {
    }

    public Recepteur(Integer id, String nomRec, String prenomRec, String telephoneRec) {
        this.id = id;
        this.nomRec = nomRec;
        this.prenomRec = prenomRec;
        this.telephoneRec = telephoneRec;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomRec() {
        return nomRec;
    }

    public void setNomRec(String nomRec) {
        this.nomRec = nomRec;
    }

    public String getPrenomRec() {
        return prenomRec;
    }

    public void setPrenomRec(String prenomRec) {
        this.prenomRec = prenomRec;
    }

    public String getTelephoneRec() {
        return telephoneRec;
    }

    public void setTelephoneRec(String telephoneRec) {
        this.telephoneRec = telephoneRec;
    }
}
