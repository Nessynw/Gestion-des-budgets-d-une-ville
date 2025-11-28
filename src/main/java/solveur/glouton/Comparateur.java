package solveur.glouton;

import sacADos.*;

import java.util.Comparator;


public class Comparateur{

        public static class ParUtiliteDecroissante implements Comparator<Objet> {
            @Override
            public int compare(Objet o1, Objet o2) {
                return o2.getUtilite() - o1.getUtilite();  // décroissant
            }
        }

        public static class ParUtiliteCroissante implements Comparator<Objet> {
            @Override
            public int compare(Objet o1, Objet o2) {
                return o1.getUtilite() - o2.getUtilite();  // croissant
            }
        }
    }

