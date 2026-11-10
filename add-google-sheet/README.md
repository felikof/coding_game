# Projet Formulaire HTML vers Google Sheets

## Description

Ce projet consiste en un formulaire HTML permettant de recueillir des informations d'utilisateur (nom, prénom, âge et téléphone) et de les envoyer à une feuille Google Sheets via l'API Google Sheets.

## Technologies utilisées

- **HTML** : Formulaire basique pour la collecte d'informations utilisateur.
- **Python** : Utilisé pour envoyer les données à Google Sheets via l'API Google Sheets.
- **gspread** : Bibliothèque Python pour interagir avec Google Sheets.
- **OAuth2** : Utilisé pour l'authentification avec les services Google via le fichier `credentials.json`.

## Structure du projet

- `form.html` : Contient le formulaire HTML.
- `send.py` : Script Python qui envoie les données du formulaire à Google Sheets.
- `credentials.json` : Fichier de clés pour l'authentification via l'API Google Sheets (doit être généré via la console Google Cloud).

## Prérequis

- **Python 3.x** doit être installé.
- Créez un projet Google Cloud et activez l'API Google Sheets.
- Téléchargez le fichier `credentials.json` depuis la console Google Cloud.
- Installez les dépendances Python en exécutant :
  ```bash
  pip install gspread oauth2client
- Cette section explique comment rendre la feuille Google Sheets publique pour permettre la lecture et l'écriture.