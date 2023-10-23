package com.btssio.models.tarif;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.List;

public class TarifManager {
    private Tarifs tarifs;
    private CategorieManager categorieManager;

    {
        categorieManager = new CategorieManager(List.of());
    }

    public CategorieManager getCategorieManager() {
        return categorieManager;
    }

    public TarifManager(String pathToXml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Tarifs.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        this.tarifs = (Tarifs) unmarshaller.unmarshal(new File(pathToXml));
        this.categorieManager = new CategorieManager(tarifs.getCategories());
    }
    public void saveToXml(String pathToXml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Tarifs.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(tarifs, new File(pathToXml));
    }


}
