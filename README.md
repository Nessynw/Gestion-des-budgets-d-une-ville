# Gestion des budgets municipaux – Optimisation combinatoire

## Description du projet

Ce projet a pour objectif de proposer une **gestion optimisée des budgets municipaux**. Chaque projet municipal possède plusieurs **coûts** (économique, social, environnemental) et un **bénéfice** associé. Le problème est modélisé comme un **sac à dos multidimensionnel** :

- Maximiser l’utilité totale (bénéfices des projets sélectionnés)
- Respecter les contraintes budgétaires sur toutes les dimensions

Deux approches d’optimisation sont implémentées :  

1. **Algorithme Glouton (Greedy)** : sélectionne itérativement les projets selon un ratio bénéfice/coût pour produire rapidement une solution réalisable.
2. **Algorithme Hill Climbing** : explore localement l’espace des solutions en générant des voisins et en choisissant à chaque itération le meilleur voisin. Supporte :
   - Mode standard : arrêt dès qu’aucune amélioration n’est possible
   - Mode plateau : autorise un nombre limité de mouvements latéraux pour échapper aux plateaux

Le projet inclut également une **conversion automatique des projets en objets du sac à dos**, permettant d’appliquer les algorithmes d’optimisation à différents formats d’entrée.
---

## Fonctionnalités principales

1. **Gestion multi-dimensionnelle des budgets** : chaque projet peut avoir plusieurs coûts, et le solveur vérifie que toutes les contraintes sont respectées.
2. **Algorithme glouton** :
   - Sélection rapide
   - Ratio bénéfice / coût
   - Solution réalisable mais pas toujours optimale
3. **Hill Climbing** :
   - Génération systématique des voisins (ajout, retrait, échange)
   - Optimisation locale vers l’utilité maximale
   - Mode plateau pour échapper aux plateaux
4. **Tests unitaires complets** :
   - Vérification de toutes les méthodes des classes métier et solveurs
   - Respect du budget, validité des solutions, fonctionnement des générateurs de voisins
---

## Installation
1. Cloner le dépôt :
```bash
git clone https://github.com/Nessynw/Gestion-des-budgets-d-une-ville.git

```
2. Compiler/exécuter

- depuis un terminal
````
mvn clean package
java -jar target/Gestion-des-budgets-d-une-ville.jar
````
- depuis un IDE:
ouvrir src>main>java>main>Main.java
---

## Structure du projet

