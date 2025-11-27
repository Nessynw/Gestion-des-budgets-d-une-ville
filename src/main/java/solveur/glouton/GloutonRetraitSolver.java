package solveur.glouton;

import sacADos.Objet;
import sacADos.SacADos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GloutonRetraitSolver {

    private final Comparator<Objet> tieBreaker; // optionnel pour départager en cas d'égalité

    public GloutonRetraitSolver() {
        this.tieBreaker = null;
    }

    public GloutonRetraitSolver(Comparator<Objet> tieBreaker) {
        this.tieBreaker = tieBreaker;
    }

    public List<Objet> resoudre(SacADos instance, Comparator<Objet> ajoutComparator) {
        GloutonAjoutSolver ajoutSolver = new GloutonAjoutSolver(ajoutComparator);
        List<Objet> selection = new ArrayList<>(ajoutSolver.resoudre(instance));

        int[] budgetRestant = instance.getBudget().clone();
        for (Objet o : selection) {
            soustraireCouts(o, budgetRestant);
        }

        while (depasseBudget(budgetRestant)) {
            Objet aRetirer = null;
            double pireRatio = Double.POSITIVE_INFINITY;

            for (Objet o : selection) {
                int coutTotal = sommeCouts(o.getCouts());
                double ratio;
                if (coutTotal == 0) {
                    ratio = Double.POSITIVE_INFINITY;
                } else {
                    ratio = (double) o.getUtilite() / coutTotal;
                }

                if (ratio < pireRatio) {
                    pireRatio = ratio;
                    aRetirer = o;
                } else if (ratio == pireRatio && tieBreaker != null && aRetirer != null) {
                    if (tieBreaker.compare(o, aRetirer) < 0) {
                        aRetirer = o;
                    }
                }
            }

            if (aRetirer == null) break;
            selection.remove(aRetirer);
            ajouterCouts(aRetirer, budgetRestant);
        }

        return selection;
    }

    // ici jai mis le comparateur par default donc du moins interessant
    public List<Objet> resoudre(SacADos instance) {
        Comparator<Objet> defaultComparator = (o1, o2) -> o2.getUtilite() - o1.getUtilite();
        return resoudre(instance, defaultComparator);
    }



    private boolean depasseBudget(int[] budget) {
        for (int b : budget) if (b < 0) return true;
        return false;
    }

    private int sommeCouts(int[] couts) {
        int s = 0;
        for (int c : couts) s += c;
        return s;
    }

    private void soustraireCouts(Objet o, int[] budget) {
        int[] couts = o.getCouts();
        for (int i = 0; i < couts.length; i++) budget[i] -= couts[i];
    }

    private void ajouterCouts(Objet o, int[] budget) {
        int[] couts = o.getCouts();
        for (int i = 0; i < couts.length; i++) budget[i] += couts[i];
    }
}
