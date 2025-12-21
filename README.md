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
## Structure du projet


---
## Prérequis
- Java JDK
- Maven
- IDE recommandé : IntelliJ IDEA

## Installation
## 1. Cloner le dépôt :
git clone https://github.com/Nessynw/Gestion-des-budgets-d-une-ville.git
cd Gestion-des-budgets-d-une-ville
## 2. Compiler le projet :
- Sur intelliJ IDEA:
  Maven se configure automatiquement via pom.xml
  Build -> Build Project
## 3. Execution :
  1- Avec IntelliJ IDEA:
  Ouvrir main/Main.java
  Run Main.main()
  Interagir avec le menu dans la console
  2- Avec Maven
  mvn exec: java -Dexec.mainClass="main.Main"
## 4. Menu Principal:
  Au lancement le programme affiche:
  === MENU ===
1. Tester l'équipe municipale
2. Tester le sac à dos
3. Tester le Glouton Ajout
4. Tester le Glouton Retrait
5. Tester le Hill Climbing
6. Quitter

Votre choix :

--> Ordre recommandé d’exécution: Option 1 → Option 2 → Options 3/4/5
## Problèmes courants : 
Erreur de compilation Maven: 
--> Sa solution : mvn clean install -U
```
2. Compiler/exécuter

  
- depuis un terminal
````
./mvnw clean package #(sur windows: mvnw.cmd clean package)
java -jar target\Gestion-des-budgets-d-une-ville--1.0-SNAPSHOT.jar
````
- depuis un IDE:
ouvrir src>main>java>main>Main.java
---


