package solveur.glouton;

import sacados.Objet;
import java.util.Comparator;

public class Comparateur {

    // Tri décroissant : le plus utile en premier
    public static class ParUtiliteDecroissante implements Comparator<Objet> {
        @Override
        public int compare(Objet o1, Objet o2) {
            return Integer.compare(o2.getUtilite(), o1.getUtilite());
        }
    }

    // Tri croissant : le moins utile en premier
    public static class ParUtiliteCroissante implements Comparator<Objet> {
        @Override
        public int compare(Objet o1, Objet o2) {
            return Integer.compare(o1.getUtilite(), o2.getUtilite());
        }
    }
}
