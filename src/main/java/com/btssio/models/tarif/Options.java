package com.btssio.models.tarif;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.btssio.models.utils.MapAdapter;

import java.util.Map;

@XmlRootElement(name = "Options")
public class Options {
    private double licenceSansAssurance;
    private double licenceAvecAssurance;
    private double carte10Seances;
    private Map<String, Double> reductionsFamiliales;

    // Getters et Setters
    @XmlElement(name = "LicenceSansAssurance")
    public double getLicenceSansAssurance() {
        return licenceSansAssurance;
    }

    public void setLicenceSansAssurance(double licenceSansAssurance) {
        this.licenceSansAssurance = licenceSansAssurance;
    }

    @XmlElement(name = "LicenceAvecAssurance")
    public double getLicenceAvecAssurance() {
        return licenceAvecAssurance;
    }

    public void setLicenceAvecAssurance(double licenceAvecAssurance) {
        this.licenceAvecAssurance = licenceAvecAssurance;
    }
    @XmlElement(name = "Carte10Seances")
    public double getCarte10Seances() {
        return carte10Seances;
    }

    public void setCarte10Seances(double carte10Seances) {
        this.carte10Seances = carte10Seances;
    }

    @XmlElement(name = "ReductionsFamiliales")
    @XmlJavaTypeAdapter(MapAdapter.class)
    public Map<String, Double> getReductionsFamiliales() {
        return reductionsFamiliales;
    }

    public void setReductionsFamiliales(Map<String, Double> reductionsFamiliales) {
        this.reductionsFamiliales = reductionsFamiliales;
    }


}
