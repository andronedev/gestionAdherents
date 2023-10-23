package com.btssio.models.tarif;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class TarifManager {
    private Tarifs tarifs;

    public TarifManager(String pathToXml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Tarifs.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        this.tarifs = (Tarifs) unmarshaller.unmarshal(new File(pathToXml));
    }
    public void saveToXml(String pathToXml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Tarifs.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(tarifs, new File(pathToXml));
    }


}
