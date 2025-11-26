
package solveur.glouton;
import sacADos.*;

import java.util.Comparator;

public class GloutonAjoutSolver {
    private final Comparator<Objet> comparator;

    public GloutonAjoutSolver(Comparator<Objet> comparateur) {
        this.comparator = comparateur;
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
    }
}

