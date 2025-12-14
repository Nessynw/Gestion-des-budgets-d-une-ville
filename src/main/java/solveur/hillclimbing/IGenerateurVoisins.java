package solveur.hillclimbing;

import java.util.List;
import sacados.*;
//toute classe qui veut generer des voisins doit avoir la methode genererVoisins
public interface IGenerateurVoisins {
    /**
     * génére l'ens des solutions voisines d'une solution courante
     * elle est appelée à chaque itération de l'algorithme de Hill Climbing
     * @param solution
     * @param probleme
     * @return
     */
    List<Solution> genererVoisins(Solution solution, SacADos probleme);
}
