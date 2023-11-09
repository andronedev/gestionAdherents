
# Documentation Technique du Projet `GestionAdherents`

## Introduction

`GestionAdherents` est une application JavaFX qui permet de gérer les informations liées aux adhérents et aux clubs. Elle offre une interface utilisateur pour ajouter, éditer, supprimer et visualiser des données structurées.

## Architecture du Projet

Le projet suit l'architecture MVC (Modèle-Vue-Contrôleur), qui sépare l'application en trois composants interconnectés. Cette séparation aide à gérer la complexité lors du développement d'applications interactives.

## Structure du Projet

```plaintext
gestionAdherents-master/
│
├── .gitignore
├── .idea/
├── .mvn/
├── README.md
├── adherents.xml
├── clubs.xml
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── btssio/
    │   │           ├── gestionadherents/        # Contrôleurs de l'application
    │   │           │   ├── ClubController.java
    │   │           │   ├── InscriptionController.java
    │   │           │   ├── MainApplication.java  # Classe principale de l'application
    │   │           │   └── MainController.java
    │   │           └── models/                   # Modèles de données
    │   │               ├── adherent/
    │   │               ├── clubs/
    │   │               ├── tarif/
    │   │               └── utils/
    │   └── resources/
    │       └── com/
    │           └── btssio/
    │               └── gestionadherents/         # Vues FXML de l'application
    │                   ├── annuaire-club-view.fxml
    │                   ├── inscription-view.fxml
    │                   ├── main-view.fxml
    │                   └── ... (autres vues FXML)
```

### Modèles

Les modèles définissent la structure des données de l'application. Dans `GestionAdherents`, les modèles sont représentés par des classes Java qui définissent les propriétés et comportements des entités telles que les adhérents et les clubs.

#### Exemple : `Adherent.java`

```java
// Un exemple de classe modèle pour les adhérents.
public class Adherent {
    private String nom;
    private String prenom;
    // autres propriétés
    
    // Constructeur, getters et setters
}
```

### Vues

Les vues sont les interfaces utilisateur avec lesquelles les utilisateurs interagissent. Dans JavaFX, elles sont souvent définies avec des fichiers `.fxml`, qui sont chargés par les contrôleurs.

#### Exemple : `main-view.fxml`

```xml
<!-- Un exemple de fichier FXML pour l'interface principale de l'application. -->
<VBox xmlns:fx="http://javafx.com/fxml" fx:controller="com.btssio.gestionadherents.MainController">
    <!-- Éléments de l'interface utilisateur -->
</VBox>
```

### Contrôleurs

Les contrôleurs gèrent la logique de l'application, répondant aux actions de l'utilisateur et mettant à jour les vues.

#### Exemple : `MainController.java`

```java
// Un exemple de contrôleur gérant la vue principale.
public class MainController {
    // Méthodes pour gérer les événements
}
```

## Détails des Contrôleurs

### `MainApplication.java`
La classe `MainApplication` est le point d'entrée de l'application JavaFX. Elle hérite de la classe `Application` et surcharge la méthode `start`, qui est le point de départ de l'interface utilisateur JavaFX.

### `MainController.java`
`MainController` sert de contrôleur principal pour la vue principale de l'application. Il gère les événements de l'interface utilisateur et gère l'affichage des adhérents, permet la modification, etc.

### `ClubController.java`
Le `ClubController` est responsable de la gestion des interactions liées aux clubs, Génération de PDFs

### `InscriptionController.java`
`InscriptionController` gère le processus d'inscription des adhérents, y compris la collecte et la validation des données d'inscription.

## Détails des Modèles de Données

### Dossier `adherent`
Le dossier `adherent` contient les classes représentant les adhérents et leurs informations associées. Par exemple, `Adherent.java` définit les propriétés d'un adhérent telles que le nom, l'adresse, etc.

### Dossier `clubs`
Le dossier `clubs` inclut les modèles pour les clubs, définissant les attributs tels que le nom du club, les membres, et les activités offertes.

### Dossier `tarif`
Dans le dossier `tarif`, les classes gèrent la tarification des adhérents et des clubs, permettant de calculer les cotisations en fonction de différentes règles et catégories.

### Dossier `utils`
Le dossier `utils` contient des classes utilitaires qui fournissent des fonctionnalités communes nécessaires à travers l'application, comme la manipulation des dates ou des fichiers XML.
