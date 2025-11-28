package solveur.glouton;

import sacADos.*;
import java.util.*;

public class GloutonAjoutSolver {

    private final Comparator<Objet> comparateur;

    public GloutonAjoutSolver(Comparator<Objet> comparateur) {
        this.comparateur = comparateur;
    }

    public List<Objet> resoudre(SacADos instance) {
        List<Objet> objets = new ArrayList<>(instance.getObjets());
        objets.sort(comparateur);

        List<Objet> solution = new ArrayList<>();
        int[] budgetRestant = instance.getBudget().clone();

        for (Objet o : objets) {
            if (peutAjouter(o, budgetRestant)) {
                solution.add(o);
                retirerCouts(o, budgetRestant);
            }
        }

        return solution;
    }

    private boolean peutAjouter(Objet o, int[] budget) {
        int[] couts = o.getCouts();
        for (int i = 0; i < couts.length; i++) {
            if (couts[i] > budget[i]) {
                return false;
            }
        }
        return true;
    }


    private void retirerCouts(Objet o, int[] budget) {
        int[] couts = o.getCouts();
        for (int i = 0; i < couts.length; i++) {
            budget[i] -= couts[i];
        }
    }}
