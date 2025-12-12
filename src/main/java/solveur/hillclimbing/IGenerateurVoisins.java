package solveur.hillclimbing;

import java.util.List;
import sacados.*;
//toute classe qui veut generer des voisins doit avoir la methode genererVoisins
public interface IGenerateurVoisins {
    List<Solution> genererVoisins(Solution solution, SacADos probleme);
}
