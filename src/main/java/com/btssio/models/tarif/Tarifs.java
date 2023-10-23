package com.btssio.models.tarif;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Tarifs")
public class Tarifs {
    private List<Categorie> categories;
    private Options options;
    private tarif autresFormules;

    // getters et setters...

    @XmlElement(name = "Categorie")
    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public tarif getAutresFormules() {
        return autresFormules;
    }

    public void setAutresFormules(tarif autresFormules) {
        this.autresFormules = autresFormules;
    }
}
