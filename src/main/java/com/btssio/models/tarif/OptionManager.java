package com.btssio.models.tarif;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.Map;

public class OptionManager {
    private static Options options;

    public OptionManager(Options options) {
        OptionManager.options = options; // Assurez-vous que options est initialisé avant de passer à OptionManager
    }

    // Méthode pour calculer le montant avec assurance renforcé ou non
    public double getLicenceAmount(boolean sansAssurance, boolean avecAssurance) {
        // Vérifier si une seule option est sélectionnée
        if (sansAssurance && !avecAssurance) {
            return options.getLicenceSansAssurance();
        } else if (!sansAssurance && avecAssurance) {
            return options.getLicenceAvecAssurance();
        } else {
            // Si aucune option ou les deux options sont sélectionnées, retourner 0
            return 0.0;
        }
    }
    // Méthode pour calculer le montant avec ou sans carte de 10 séances
    public double getCarte10SeancesAmount(boolean avec10SeanceCheckbox) {
        return avec10SeanceCheckbox ? options.getCarte10Seances() : 0.0;
    }

    public static double calculerReduction(int nombreAdherents, String categorie) {
        // Détermine le suffixe de la clé basé sur le nombre d'adhérents
        if (nombreAdherents < 2) {
            return 0.0;
        }

        String type = nombreAdherents == 2 ? "2eAdherent" : "3eAdherent";

        // Forme la clé en combinant catégorie et type
        String reductionKey = categorie + "," + type;

        // Obtient la map des réductions et cherche la valeur avec la clé formée
        Map<String, Double> reductions = options.getReductionsFamiliales();  // Assurez-vous que cette Map est de type <String, Double>
        Double reductionValue = reductions.get(reductionKey);

        // Retourne la valeur trouvée, ou 0.0 si aucune réduction n'est trouvée
        return reductionValue != null ? reductionValue : 0.0;
    }
    //methode pour calculer le montant total
    public double calculerMontantTotal(double montantAdhesion, double montantOption, double montantReduction , double montantCarte10Seances) {
        return montantAdhesion + montantOption + montantReduction + montantCarte10Seances;
    }
    public void loadFromXml(String xmlFilePath) throws JAXBException {
        File xmlFile = new File(xmlFilePath);
        JAXBContext context = JAXBContext.newInstance(Tarifs.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Tarifs tarifs = (Tarifs) unmarshaller.unmarshal(xmlFile);
        options = tarifs.getOptions(); // Assurez-vous que Tarifs contient un getter pour Options
    }
}
