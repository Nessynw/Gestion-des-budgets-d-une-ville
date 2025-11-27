package solveur.glouton;

import sacADos.Objet;
import sacADos.SacADos;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GloutonAjoutSolver {

    private final Comparator<Objet> comparateur;

    public GloutonAjoutSolver(Comparator<Objet> comparateur) {
        this.comparateur = comparateur;
    }

    public List<Objet> resoudre(SacADos instance) {

        List<Objet> objets = new ArrayList<>(instance.getObjets());

        // c pour trier ici
        objets.sort(comparateur);

        List<Objet> solution = new ArrayList<>();

        //jai du  récupérer le budget avec getBudget() ici
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
                return false; // si on depasse le budget on peut pas ajouter
            }
        }
        return true;
    }

    private void retirerCouts(Objet o, int[] budget) {
        int[] couts = o.getCouts();
        for (int i = 0; i < couts.length; i++) {
            budget[i] -= couts[i];
        }
    }
}
