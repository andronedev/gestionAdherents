package com.btssio.models.tarif;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Options")
public class Options {
    private double licenceSansAssurance;
    private double licenceAvecAssurance;
    private String reductionFamille2e;
    private String reductionFamille3e;

    // Getters et Setters
    // ...
}
