package com.btssio.models;

import java.io.File;
import java.util.ArrayList;

// pour la gestion des fichiers XML
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;



public class AdherentManager {

    private ArrayList<Adherent> adherents = new ArrayList<Adherent>();
    private ArrayList<Club> clubs = new ArrayList<Club>();
    public AdherentManager() {
    }

    // Ajout d'un adhérent
    public void ajouterAdherent(Adherent adherent) {
        adherents.add(adherent);
    }

    // Recherche d'un adhérent
    public Adherent rechercherAdherent(String nom) {
        for (Adherent adherent : adherents) {
            if (adherent.getNom().equals(nom)) {
                return adherent;
            }
        }
        return null;
    }

    // Modification d'un adhérent
    public void modifierAdherent(Adherent adherent) {
        Adherent a = rechercherAdherent(adherent.getNom());
        if (a != null) {
            a.setPrenom(adherent.getPrenom());
            // etc.
        }
    }

    // Suppression d'un adhérent
    public void supprimerAdherent(Adherent adherent) {
        adherents.remove(adherent);
    }

    // Récupération de tous les adhérents
    public ArrayList<Adherent> getAdherents() {
        return adherents;
    }

    // Chargement des adhérents depuis un fichier XML
    public void loadAdherentsFromXML(String path) {
        // Code pour charger les adherents à partir du fichier XML


        // si le fichier n'existe pas, on crée un fichier vide
        if (!new File(path).exists()) {
            try {
                new File(path).createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }


        File file = new File(path);


        // Lecture du fichier XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document = null;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Parcours du fichier XML et création des adhérents

        for (int i = 0; i < document.getElementsByTagName("adherent").getLength(); i++) {
            String nom = document.getElementsByTagName("nom").item(i).getTextContent();
            String prenom = document.getElementsByTagName("prenom").item(i).getTextContent();
            Adherent adherent = new Adherent(nom, prenom);
            adherents.add(adherent);
        }





    }

    public void loadClubsFromXML(String path) {
        if (!new File(path).exists()) {
            try {
                new File(path).createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        File file = new File(path);


        // Lecture du fichier XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document = null;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }


        for (int i = 0; i < document.getElementsByTagName("club").getLength(); i++) {
            String nom = document.getElementsByTagName("nom").item(i).getTextContent();
            String adresse = document.getElementsByTagName("adresse").item(i).getTextContent();
            Club club = new Club(nom, adresse);
            clubs.add(club);
        }


    }


    //getAdherentsTableModel
    public TableModel getAdherentsTableModel(String nom) {
        ArrayList<Adherent> adherents_searched;
        if (nom == null || nom.equals("")) {
            adherents_searched = adherents;
        } else {
            adherents_searched = searchAdherents(nom);
        }

        return new TableModel() {

            public int getRowCount() {
                return adherents_searched.size();
            }

            public int getColumnCount() {
                return 2; // nom et prénom
            }

            public String getColumnName(int columnIndex) {
                if (columnIndex == 0) {
                    return "Nom";
                } else {
                    return "Prénom";
                }
            }

            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                Adherent adherent = adherents_searched.get(rowIndex);
                if (columnIndex == 0) {
                    return adherent.getNom();
                } else {
                    return adherent.getPrenom();
                }
            }

            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                // left empty since cells are not editable
            }

            // Méthodes pour écouter les modifications du modèle
            public void addTableModelListener(TableModelListener l) {}
            public void removeTableModelListener(TableModelListener l) {}
        };
    }


    //searchAdherents
    public ArrayList<Adherent> searchAdherents(String query) {
        ArrayList<Adherent> result = new ArrayList<Adherent>();
        for (Adherent adherent : adherents) {
            if (adherent.getNom().contains(query) || adherent.getPrenom().contains(query)) {
                result.add(adherent);
            }
        }
        return result;
    }


}