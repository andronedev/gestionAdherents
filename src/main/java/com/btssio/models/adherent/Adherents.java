package com.btssio.models.adherent;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "adherents")
public class Adherents {
    private List<Adherent> adherentsList;

    @XmlElement(name = "adherent")
    public List<Adherent> getAdherentsList() {
        return adherentsList;
    }

    public void setAdherentsList(List<Adherent> adherentsList) {
        this.adherentsList = adherentsList;
    }
}
