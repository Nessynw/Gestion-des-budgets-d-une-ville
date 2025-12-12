package solveur.hillclimbing;

import sacados.SacADos;
import java.util.*;

public class GenerateurVoisinsBas implements IGenerateurVoisins {
    private final int t; // Nombre max d'objets à échanger

    public GenerateurVoisinsBas(int t) {
        this.t = t;
    }

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

    private Set<Integer> getObjetsDisponibles(Set<Integer> objetsActuels, SacADos probleme) {
        Set<Integer> disponibles = new HashSet<>();
        for (int i = 0; i < probleme.getDimension(); i++) {
            if (!objetsActuels.contains(i)) {
                disponibles.add(i);
            }
        }
        return disponibles;
    }

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

    private void genererEchanges(Set<Integer> objetsActuels, Set<Integer> objetsDisponibles,
                                 Set<Solution> voisins, SacADos probleme) {
        List<Integer> aRetirer = new ArrayList<>(objetsActuels);
        List<Integer> aAjouter = new ArrayList<>(objetsDisponibles);

        for (int nbRetrait = 1; nbRetrait <= t && nbRetrait <= aRetirer.size(); nbRetrait++) {
            for (int nbAjout = 1; nbAjout <= t && nbAjout <= aAjouter.size(); nbAjout++) {
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

    private void ajouterSiValide(Solution solution, Set<Solution> voisins, SacADos probleme) {
        if (solution.respecteBudget(probleme)) {
            voisins.add(solution);
        }
    }

    private void genererCombinaisons(List<Integer> elements, int k,
                                     java.util.function.Consumer<List<Integer>> action) {
        genererCombinaisonsRec(elements, k, 0, new ArrayList<>(), action);
    }

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