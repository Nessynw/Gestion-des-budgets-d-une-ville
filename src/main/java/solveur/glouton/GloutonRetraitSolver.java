package solveur.glouton;

import sacados.*;

import java.util.*;

public class GloutonRetraitSolver {

    private final Comparator<Objet> retraitComparator;

    public GloutonRetraitSolver() {
        this.retraitComparator = new Comparateur.ParUtiliteCroissante();
    }
    public GloutonRetraitSolver(Comparator<Objet> retraitComparator) {
        this.retraitComparator = retraitComparator;
    }

    public List<Objet> resoudre(SacADos instance, Comparator<Objet> ajoutComparator) {
        List<Objet> selection = new ArrayList<>(instance.getObjets()); //S=O
        selection.sort(retraitComparator);

        int[] budgetRestant = instance.getBudget().clone();

        for (Objet o : selection) {
            soustraireCouts(o, budgetRestant);
        }

        Iterator<Objet> it = selection.iterator();
        while (it.hasNext()) {
            Objet o = it.next();

            if (depasseBudget(budgetRestant)) {
                it.remove();
                ajouterCouts(o, budgetRestant);
            } else {
                //budget okay
                break;
            }
        }
        List<Objet> objetsRetires = new ArrayList<>(instance.getObjets());
        objetsRetires.removeAll(selection);
        objetsRetires.sort(ajoutComparator);


        for (Objet o : objetsRetires) {
            if (peutAjouter(o, budgetRestant)) {
                selection.add(o);
                soustraireCouts(o, budgetRestant);
            }
        }
        return selection;
    }

    public  List<Objet> resoudre(SacADos instance) {
        return resoudre(instance, new Comparateur.ParUtiliteDecroissante());
    }

    private boolean depasseBudget(int[] budget) {
        for (int b : budget) if (b < 0) return true;
        return false;
    }

    private boolean peutAjouter(Objet o, int[] budget) {
        int[] couts = o.getCouts();
        for (int i = 0; i < couts.length; i++) {
            if (couts[i] > budget[i]) return false;
        }
        return true;
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



