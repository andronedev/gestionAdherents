package com.btssio.models.tarif;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class tarif {
    public double getCarte10Seances() {
        return carte10Seances;
    }

    public void setCarte10Seances(double carte10Seances) {
        this.carte10Seances = carte10Seances;
    }

    private double carte10Seances;

    // getters et setters...
}
