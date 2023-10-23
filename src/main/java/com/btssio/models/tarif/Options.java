package com.btssio.models.tarif;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Options {
    private double licenceSansAssurance;
    private double licenceAvecAssurance;

    @XmlElement(name = "ReductionFamille2e")
    private List<Double> reductionsFamille2e;
    @XmlElement(name = "ReductionFamille3e")
    private List<Double> reductionsFamille3e;

    public double getLicenceSansAssurance() {
        return licenceSansAssurance;
    }

    public void setLicenceSansAssurance(double licenceSansAssurance) {
        this.licenceSansAssurance = licenceSansAssurance;
    }

    public double getLicenceAvecAssurance() {
        return licenceAvecAssurance;
    }

    public void setLicenceAvecAssurance(double licenceAvecAssurance) {
        this.licenceAvecAssurance = licenceAvecAssurance;
    }

    public List<Double> getReductionsFamille2e() {
        return reductionsFamille2e;
    }

    public void setReductionsFamille2e(List<Double> reductionsFamille2e) {
        this.reductionsFamille2e = reductionsFamille2e;
    }

    public List<Double> getReductionsFamille3e() {
        return reductionsFamille3e;
    }

    public void setReductionsFamille3e(List<Double> reductionsFamille3e) {
        this.reductionsFamille3e = reductionsFamille3e;
    }

    // getters et setters...
}
