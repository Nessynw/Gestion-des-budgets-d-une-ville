package solveur.glouton;

import sacADos.Objet;
import java.util.Comparator;


public class Comparateur {
    public int compare(Objet o1, Objet o2) {
        // ici jai trié par utilite decroissante ( en gros le plus utile)
        return o2.getUtilite() - o1.getUtilite();
    }
}

