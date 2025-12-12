package solveur.hillclimbing;

import sacados.*;
import java.util.List;

public class HillClimbingSolver {
    private final SacADos sacADos;
    private final IGenerateurVoisins generateurVoisins;
    private final boolean autoriserPlateau;
    private final int maxmovePlateau;
    private int iterations;
    private long tempsExecution;
    public HillClimbingSolver(SacADos sacADos, int t) {
        this(sacADos, t, false, 0);
    }
    public HillClimbingSolver(SacADos sacADos, int t, boolean autoriserPlateau, int maxmovePlateau) {
        this(sacADos, new GenerateurVoisinsBas(t), autoriserPlateau, maxmovePlateau);
    }
    public HillClimbingSolver(SacADos sacADos, IGenerateurVoisins generateurVoisins, 
            boolean autoriserPlateau, int maxmovePlateau) {
        if (sacADos == null) {
            throw new IllegalArgumentException("Le sacADos ne peut pas être null");
        }
        if (generateurVoisins == null) {
            throw new IllegalArgumentException("Le générateur de voisins ne peut pas être null");
        }
        if (maxmovePlateau < 0) {
            throw new IllegalArgumentException("Le nombre maximum de mouvements sur un plateau ne peut pas être négatif");
        }
        
        this.sacADos = sacADos;
        this.generateurVoisins = generateurVoisins;
        this.autoriserPlateau = autoriserPlateau;
        this.maxmovePlateau = maxmovePlateau;
    }
    public Solution resoudre(Solution solution) {
        if (solution == null) {
            throw new IllegalArgumentException("La solution initiale ne peut pas être null");
        }
        
        long debut = System.currentTimeMillis();

        if (!solution.respecteBudget(sacADos)) {
            throw new IllegalArgumentException("Solution initiale non réalisable");
        }

        Solution courante = solution;
        double valeurCourante = calculerUtilite(courante);
        courante.setValeur(valeurCourante);
        
        iterations = 0;
        int compteurPlateau = 0;

        while (true) {
            iterations++;

            List<Solution> voisins = generateurVoisins.genererVoisins(courante, sacADos);
            if (voisins.isEmpty()) {
                break;
            }

            Solution meilleurVoisin = trouverMeilleur(voisins);
            double valeurVoisin = meilleurVoisin.getValeur();

            if (valeurVoisin > valeurCourante) {
                courante = meilleurVoisin;
                valeurCourante = valeurVoisin;
                compteurPlateau = 0;
            } else if (autoriserPlateau && valeurVoisin == valeurCourante 
                    && compteurPlateau < maxmovePlateau) {
                courante = meilleurVoisin;
                compteurPlateau++;
            } else {
                break;
            }
        }

        tempsExecution = System.currentTimeMillis() - debut;
        return courante;
    }
    private Solution trouverMeilleur(List<Solution> voisins) {
        Solution meilleur = voisins.get(0);
        double meilleureValeur = calculerUtilite(meilleur);
        meilleur.setValeur(meilleureValeur);

        for (int i = 1; i < voisins.size(); i++) {
            Solution voisin = voisins.get(i);
            double valeur = calculerUtilite(voisin);
            voisin.setValeur(valeur);

            if (valeur > meilleureValeur) {
                meilleur = voisin;
                meilleureValeur = valeur;
            }
        }

        return meilleur;
    }

    /**
     * Calculer l'utilité d'une solution donnée
     */
    private double calculerUtilite(Solution solution) {
        double total = 0.0;
        List<Objet> objets = sacADos.getObjets();
        for (int indice : solution.getObjets()) {
            total += objets.get(indice).getUtilite();
        }
        return total;
    }


    public int getIterations() {
        return iterations;
    }
    public long getTempsExecution() {
        return tempsExecution;
    }


}
