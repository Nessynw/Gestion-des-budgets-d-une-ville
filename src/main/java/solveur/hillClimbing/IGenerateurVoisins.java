package solveur.hillClimbing;

import java.util.List;
//toute classe qui veut generer des voisins doit avoir la methode genererVoisins
public interface IGenerateurVoisins {
    List<Solution> genererVoisins(Solution solution);
}
