package solveur.hillclimbing;

import sacados.SacADos;
import java.util.*;

/**
 * générateur de voisins pour un algorithme de hill climbing
 * il produit des solutions voisines en effectuant des modification slocales sur une solution courante:
 * Retrait de 1 à t objets
 * Ajout de 1 à t objets
 * échange d'objets (retrait + ajout), avec un total max de t objets modifiés
 *
 * les solutions générées respectent toujours la contrainte de budget du problème
 */
public class GenerateurVoisinsBas implements IGenerateurVoisins {
    private final int t; // Nombre max d'objets à échanger

    /**
     * constructeur d'un générateur de voisins avec une borne sur le nbr de modification autorisées
     * @param t : nbr max de d'objets pouvant être modifiés
     */
    public GenerateurVoisinsBas(int t) {
        this.t = t;
    }

    /**
     * génére l'ensemble des solutions voisines d'une solution donnée selon les trois stratégies.
     * @param solution sol courante
     * @param probleme instance du pb du sac à dos
     * @return liste des solutions voisines valides (sans doublons)
     */
    @Override
    public List<Solution> genererVoisins(Solution solution, SacADos probleme) {
        Set<Solution> voisins = new HashSet<>(); // Évite automatiquement les doublons
        Set<Integer> objetsActuels = solution.getObjets();
        Set<Integer> objetsDisponibles = getObjetsDisponibles(objetsActuels, probleme);

        // Cas 1: Retirer uniquement
        genererRetraits(objetsActuels, objetsDisponibles, voisins, probleme);

        // Cas 2: Ajouter uniquement
        genererAjouts(objetsActuels, objetsDisponibles, voisins, probleme);

        // Cas 3: Échanger (retirer ET ajouter)
        genererEchanges(objetsActuels, objetsDisponibles, voisins, probleme);

        return new ArrayList<>(voisins);
    }

    /**
     * déterminer l'ens des objets non présents dans la solution courante
     * @param objetsActuels
     * @param probleme
     * @return ensemble des objets disponibles à l'ajout
     */

    private Set<Integer> getObjetsDisponibles(Set<Integer> objetsActuels, SacADos probleme) {
        Set<Integer> disponibles = new HashSet<>();
        for (int i = 0; i < probleme.getDimension(); i++) {
            if (!objetsActuels.contains(i)) {
                disponibles.add(i);
            }
        }
        return disponibles;
    }

    /**
     * génére des voisins en retirant entre 1 et t objets de la sol courante
     * @param objetsActuels objets selectionnés
     * @param objetsDisponibles objets non selectionnés
     * @param voisins ens des solutions voisines générées
     * @param probleme instance du sac à dos
     */

    private void genererRetraits(Set<Integer> objetsActuels, Set<Integer> objetsDisponibles,
                                 Set<Solution> voisins, SacADos probleme) {
        List<Integer> liste = new ArrayList<>(objetsActuels);

        for (int nb = 1; nb <= t && nb <= liste.size(); nb++) {
            genererCombinaisons(liste, nb, combinaison -> {
                Set<Integer> nouveaux = new HashSet<>(objetsActuels);
                nouveaux.removeAll(combinaison);
                ajouterSiValide(new Solution(nouveaux), voisins, probleme);
            });
        }
    }

    /**
     * génére des voisins en ajoutant entre 1 et t objets à la sol courante
     * @param objetsActuels
     * @param objetsDisponibles dispo à l'ajout
     * @param voisins
     * @param probleme
     */
    private void genererAjouts(Set<Integer> objetsActuels, Set<Integer> objetsDisponibles,
                               Set<Solution> voisins, SacADos probleme) {
        List<Integer> liste = new ArrayList<>(objetsDisponibles);

        for (int nb = 1; nb <= t && nb <= liste.size(); nb++) {
            genererCombinaisons(liste, nb, combinaison -> {
                Set<Integer> nouveaux = new HashSet<>(objetsActuels);
                nouveaux.addAll(combinaison);
                ajouterSiValide(new Solution(nouveaux), voisins, probleme);
            });
        }
    }

    /**
     * génére des voisins en echangeant des objets: retrait d'objets présent et ajout d'objets absents
     * avec un nbr total de modification <= t
     * @param objetsActuels
     * @param objetsDisponibles
     * @param voisins
     * @param probleme
     */
    private void genererEchanges(Set<Integer> objetsActuels, Set<Integer> objetsDisponibles,
                                 Set<Solution> voisins, SacADos probleme) {
        List<Integer> aRetirer = new ArrayList<>(objetsActuels);
        List<Integer> aAjouter = new ArrayList<>(objetsDisponibles);

        for (int nbRetrait = 1; nbRetrait <= t; nbRetrait++) {
            for (int nbAjout = 1; nbAjout <= t - nbRetrait; nbAjout++) {
                int finalNbAjout = nbAjout;

                genererCombinaisons(aRetirer, nbRetrait, retraits -> {
                    genererCombinaisons(aAjouter, finalNbAjout, ajouts -> {
                        Set<Integer> nouveaux = new HashSet<>(objetsActuels);
                        nouveaux.removeAll(retraits);
                        nouveaux.addAll(ajouts);
                        ajouterSiValide(new Solution(nouveaux), voisins, probleme);
                    });
                });
            }
        }
    }

    /**
     * ajoute une solution à l'ensemble des voisins si elle respecte la contrainte de budget
     * @param solution
     * @param voisins
     * @param probleme
     */
    private void ajouterSiValide(Solution solution, Set<Solution> voisins, SacADos probleme) {
        if (solution.respecteBudget(probleme)) {
            voisins.add(solution);
        }
    }

    /**
     * génére toutes les combinaisons possibles de k éléments à partir d'une liste donnée
     * @param elements
     * @param k taille des ocmbinaisons
     * @param action action à appliquer sur chaque combinaison générée
     */
    private void genererCombinaisons(List<Integer> elements, int k,
                                     java.util.function.Consumer<List<Integer>> action) {
        genererCombinaisonsRec(elements, k, 0, new ArrayList<>(), action);
    }

    /**
     * méthode récursive de génération de combinaisons
     * @param elements
     * @param k
     * @param debut indice de départ
     * @param courante combinaison en cours de construction
     * @param action
     */
    private void genererCombinaisonsRec(List<Integer> elements, int k, int debut,
                                        List<Integer> courante,
                                        java.util.function.Consumer<List<Integer>> action) {
        if (courante.size() == k) {
            action.accept(new ArrayList<>(courante));
            return;
        }

        for (int i = debut; i < elements.size(); i++) {
            courante.add(elements.get(i));
            genererCombinaisonsRec(elements, k, i + 1, courante, action);
            courante.remove(courante.size() - 1);
        }
    }
}