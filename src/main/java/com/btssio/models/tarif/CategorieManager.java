package com.btssio.models.tarif;

import java.util.ArrayList;
import java.util.List;

public class CategorieManager {

    private List<Categorie> categories;

    public CategorieManager(List<Categorie> categories) {
        this.categories = new ArrayList<>();
    }



    // Ajouter une catégorie
    public void addCategorie(Categorie categorie) {
        this.categories.add(categorie);
    }

    // Supprimer une catégorie par nom
    public void removeCategorie(String nom) {
        categories.removeIf(categorie -> categorie.getNom().equals(nom));
    }

    // Obtenir une catégorie par nom
    public Categorie getCategorieByName(String nom) {
        for (Categorie categorie : categories) {
            if (categorie.getNom().equals(nom)) {
                return categorie;
            }
        }
        return null;  // Renvoie null si aucune catégorie ne correspond au nom donné
    }

    // Liste de toutes les catégories
    public List<Categorie> getCategories() {
        return this.categories;
    }

    // Mettre à jour une catégorie
    public void updateCategorie(Categorie oldCategorie, Categorie newCategorie) {
        int index = categories.indexOf(oldCategorie);
        if (index != -1) {
            categories.set(index, newCategorie);
        }
    }


}
