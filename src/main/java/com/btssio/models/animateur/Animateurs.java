package com.btssio.models.animateur;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "animateurs")
public class Animateurs {
    private List<Animateur> animateursList;

    @XmlElement(name = "animateur")
    public List<Animateur> getAnimateursList() {
        return animateursList;
    }

    public void setAnimateursList(List<Animateur> animateursList) {
        this.animateursList = animateursList;
    }
}
