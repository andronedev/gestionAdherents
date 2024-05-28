package com.btssio.models.clubs;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "clubs")
public class Clubs {
    private List<Club> clubsList;

    @XmlElement(name = "club")
    public List<Club> getClubsList() {
        return clubsList;
    }

    public void setClubsList(List<Club> clubsList) {
        this.clubsList = clubsList;
    }
}
