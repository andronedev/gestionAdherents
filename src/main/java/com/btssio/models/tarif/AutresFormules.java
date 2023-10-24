package com.btssio.models.tarif;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AutresFormules")
public class AutresFormules {
    private int carte10Seances;

    @XmlElement(name = "Carte10Seances")
    public int getCarte10Seances() {
        return carte10Seances;
    }

    public void setCarte10Seances(int carte10Seances) {
        this.carte10Seances = carte10Seances;
    }
}
