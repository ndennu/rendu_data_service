# Rendu data service

## Prérequis
```
    - Android studio: 3.2.1
    - Node: 8.11.4
    - npm: 5.60
    - Wamp: 3.1.3
```

- Cloner le repo !

## Importation de la base de données

- Récupérer le fichier `dataservice_db.sql` du dossier `sql`
- Lancer `Wamp et phpMyadmin`
- Créer une nouvelle base de données nommée:

```
    dataservicev2
```

- Importer le fichier `dataservice_db.sql` dans cette base de données

## Partie server

- Se déplacer dans le dossier `nodeJs_server`
- Ouvrir une console (invite de commande)
- Exécuter la commande suivante:

```
    npm install
```

- Une fois l'installation terminé éxécuté:

```
    node .
```

Le serveur se lancera sur l'ip de votre machine et avec le port 3000.

## Partie android

- Ouvrir dans Android Studio le dossier `android_client`
- Patienter pendant que les fichiers s'index

**/!\ Important avant de run l'application**

- Ouvrir le fichier `Consts.java` avec le raccourcis **`Ctrl+Maj+N`**

- Modifier la variable `API_URL` avec l'ip de la machine qui éxécute le serveur nodeJS.

**NE PAS METTRE => localhost:3000**

- Une fois ceci fait, run l'application sur un similateur ou un device avec `une API >= 21 (Lollipop	5.0)`

## Jeu de donnée

- Pour la connexion utiliser ce jeu de donnée:

```
    user: user@gmail.com
    password: azertyuiop
```

## Dev by

**Développé par POINCET Nicolas & DENNU Nicolas, étudiant de 5 ème année à l'ESGI**
