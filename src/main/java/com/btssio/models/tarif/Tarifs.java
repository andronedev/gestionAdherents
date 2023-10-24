package com.btssio.models.tarif;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "Tarifs")
public class Tarifs {
    private List<Categorie> categories;
    private Options options;
    private AutresFormules autresFormules;

    @XmlElementWrapper(name = "Categories")
    @XmlElement(name = "Categorie")
    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    @XmlElement(name = "Options")
    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    @XmlElement(name = "AutresFormules")
    public AutresFormules getAutresFormules() {
        return autresFormules;
    }

    public void setAutresFormules(AutresFormules autresFormules) {
        this.autresFormules = autresFormules;
    }
}
