package com.cm.gestionpharmacie.model;

public class Envoi {
    private Integer id;
    private String dateEnvoi;
    private Double montant;
    private Emetteur emetteur;
    private Recepteur recepteur;

    public Envoi() {
    }

    public Envoi(Integer id, String dateEnvoi, Double montant, Emetteur emetteur, Recepteur recepteur) {
        this.id = id;
        this.dateEnvoi = dateEnvoi;
        this.montant = montant;
        this.emetteur = emetteur;
        this.recepteur = recepteur;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(String dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Emetteur getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(Emetteur emetteur) {
        this.emetteur = emetteur;
    }

    public Recepteur getRecepteur() {
        return recepteur;
    }

    public void setRecepteur(Recepteur recepteur) {
        this.recepteur = recepteur;
    }
}
