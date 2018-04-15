# Projet DevOps Pandas Pour Java [![codecov](https://codecov.io/gh/TheSpyGeek/DevOps_Project/branch/master/graph/badge.svg?token=gK8JynLGcl)](https://codecov.io/gh/TheSpyGeek/DevOps_Project) [![Build Status](https://travis-ci.com/TheSpyGeek/DevOps_Project.svg?token=AmtD253suzrd44ZDSspK&branch=master)](https://travis-ci.com/TheSpyGeek/DevOps_Project)

### Réalisé par Maxime Isnel et Victor Baverel


## Implémentation

### Fonctionnalitées réalisées

* Possibilité de construire un DataFrame à partir d'un fichier CSV
* Affichage d'un DataFrame
* Sélection sur un DataFrame par ligne ou par colonne
* Diverses opérations sur les colonnes
    - Moyenne
    - Somme
    - Maximum
    - Minimum

* Implémentation du GroupBy sur une OU plusieurs colonnes
* Possibilités de réaliser diverses opération d'aggrégation sur un GroupBy
    - Moyenne
    - Somme
    - Count
    - Maximum
    - Minimum
* Avec la possibilité d'afficher les résultats de ces opération d'aggrégations

### Contraintes imposées par notre implémentation

Toutes les colonnes d'un DataFrame doivent avoir le même nombre d'éléments. Aussi, un fichier CSV ne peut pas avoir de cases vides.

Impossible de réaliser une opération d'aggrégation sur une des colonnes groupées (dans le cas d'un GroupBy)

## Intégration Continue

Utilisation de Travis-ci.
Utilisation de Cobertura pour la couverture de code.


Faire mvn site pour générer le site relatif à la couverture de code de nos tests. (le rapport se trouve dans target/site)

Utilisation du site codecov.io nous permettant d'avoir un suivi en ligne de nos rapports de couverture de code.

De plus, l'utilisation d'un site externe nous a aussi permis de bénéficier du badge de couverture de code pour github (non négligeable :-) )


## Docker

Pour lancer la librairie avec docker il faut lancer cette commande:

sudo docker run --mount type=bind,source=FileTest,target=/DevOps_Project/ressources thespygeek/javapanda

FileTest est un dossier (chemin absolu) contenant un fichier "file.csv" sur lequel des stats vont être faites