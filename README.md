
# GestionAdherents

Une application JavaFX pour la gestion des adhérents, des clubs et des tarifs associés.

## Prérequis

- Java 17
- Maven

## Installation

Clonez ce dépôt et ouvrez-le avec votre IDE préféré. Assurez-vous que Java 17 est configuré comme SDK du projet et que Maven est installé.

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

## Utilisation

Pour construire et exécuter l'application, exécutez les commandes Maven suivantes dans votre terminal :

```shell
mvn clean install   # Nettoie et construit le projet
mvn javafx:run      # Exécute l'application
```

## Contribution

Les contributions sont les bienvenues. Suivez les étapes suivantes pour contribuer au projet :

1. Fork le dépôt.
2. Créez une nouvelle branche pour vos modifications.
3. Faites vos changements et ajoutez des commentaires JavaDoc pertinents.
4. Poussez vos changements sur votre fork.
5. Soumettez une pull request au dépôt original.
