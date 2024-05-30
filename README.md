# AuctionApp - Backend

## Description
Ce projet est une application Web en Java Spring Boot qui fournit une API RESTful afin de gérer les ventes aux enchères.

L'application inclut les fonctionnalités suivantes :

 - Opérations CRUD pour les catégories, lots, enchères et utilisateurs.
 - Gestion des exceptions pour les entités non trouvées.
 - Chargement initial des données.
 - Initialisation d'utilisateurs tests.

## Structure des Fichiers

### Configuration
 - **CorsConfig.java** : Configuration CORS pour permettre les requêtes cross-origin.
 - **IdUtil.java** : Configuration pour encoder et décoder l'id pour l'url de détails.

### Contrôleurs
 - **CategoryController.java** : Gère les requêtes HTTP pour les catégories.
 - **LotController.java** : Gère les requêtes HTTP pour les lots.
 - **EnchereController.java** : Gère les requêtes HTTP pour les enchères.
 - **CustomerController.java** : Gère les requêtes HTTP pour les utilisateurs.

### Modèles
 - **Category.java** : Représente l'entité Catégorie.
 - **Lot.java** : Représente l'entité Lot.
 - **Enchere.java** : Représente l'entité Enchère.
 - **Customer.java** : Représente l'entité Utilisateur.

### Répertoires
 - **CategoryRepository.java** : Interface pour l'accès aux données des catégories.
 - **LotRepository.java** : Interface pour l'accès aux données des lots.
 - **EnchereRepository.java** : Interface pour l'accès aux données des enchères.
 - **CustomerRepository.java** : Interface pour l'accès aux données des utilisateurs.
 - **LoadDatabase.java** : Classe pour charger les données initiales dans la base de données.

### Exceptions
 - **CategoryNotFoundException.java** : Exception personnalisée pour catégorie non trouvée.
 - **LotNotFoundException.java** : Exception personnalisée pour lot non trouvé.
 - **UserNotFoundException.java** : Exception personnalisée pour utilisateur non trouvé.
 - **EnchereNotFoundException.java** : Exception personnalisée pour enchère non trouvée.

### Application Principale
 - **AuctionApplication.java** : Classe principale pour démarrer l'application Spring Boot.

### Configuration de l'application
 - **application.properties** : Fichier de configuration pour l'application Spring Boot.

## Comment Exécuter
 1. Clonez le dépôt.
 ```sh git clone [URL du dépôt]

 2. Assurez-vous d'avoir Java et Maven installés.
Téléchargez Maven à partir de ce lien :
```sh https://maven.apache.org/download.cgi 

 3. Décompressez le fichier téléchargé à l'emplacement souhaité.
Ajoutez le chemin bin de Maven aux variables d'environnement. Par exemple :
```sh C:\Program Files\apache-maven-3.8.6\bin

 4. Naviguez jusqu'au répertoire du projet
```sh cd [nom_du_répertoire]

 5. Exécutez l'application AuctionApplication avec la commande suivante :
```sh mvn spring-boot:run

 6. Lancer le projet BackEnd avant le projet FrontEnd afin de faire venir les données
```sh run AuctionApplication

## Contact
Ce readme présente les informations nécessaires pour comprendre, configurer et exécuter le projet backend AuctionApp. Pour toute question ou assistance, n'hésitez pas à nous contacter via cette adresse mail : evan.pineau@etml-es.ch ou nicolas.lema@etml-es.ch.
