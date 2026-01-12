package solveur.hillclimbing;

import sacados.*;
import java.util.List;

/**
 * implémentation de l'algorithme d'optimisation HillClimbing pour résoudre le problème du sac à dos.
 * Hill Climbing est une méthode de recherche locale qui explore itérativement les solutions voisines en choisissant toujours
 * la solution qui améliore le plus l'objectif (ici l'utilité totale).
 * l'algorithme s'arrête lorsqu'aucun voisin n'améliore la solution courante.
 *
 * Cette implémentation supporte deux modes:
 * standard: l'algo s'arrête dès qu'aucune amélioration n'est possible
 * avec plateau: autorise un nbr limité de mouvements latéraux (même utilité) pour échapper aux plateaux
 *
 * le générateur de voisins utilisé détermine la stratégie d'exploration de l'espace de recherche.
 */
public class HillClimbingSolver {
    private final SacADos sacADos;
    private final IGenerateurVoisins generateurVoisins;
    private final boolean autoriserPlateau; //indique si les utilités identiques sont autorisés
    private final int maxmovePlateau; // nbr max de mouvements autorisés sur un plateau
    private int iterations;
    private long tempsExecution; //temps d'exécution de la dernière résolution

    /**
     * conctructeur d'un solveur hill climbing en mode standard
     * @param sacADos problème à résoudre
     * @param t le paramètre de taille pour le générateur de voisins
     */
    public HillClimbingSolver(SacADos sacADos, int t) {
        this(sacADos, t, false, 0);
    }

    /**
     * conctructeur d'un solveur hill climbing en mode plateau
     * @param sacADos
     * @param t
     * @param autoriserPlateau pour autoriser les mouvements latéraux sur les plateaux
     * @param maxmovePlateau ignoré si autoriserPlateau est faux
     */
    public HillClimbingSolver(SacADos sacADos, int t, boolean autoriserPlateau, int maxmovePlateau) {
        this(sacADos, new GenerateurVoisinsBas(t), autoriserPlateau, maxmovePlateau);
    }

    /**
     * constructeur d'une solveur hill climbing avec un générateur de voisins personnalisé
     * @param sacADos ne peut pas être null
     * @param generateurVoisins ne peut pas être null
     * @param autoriserPlateau
     * @param maxmovePlateau
     */
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

    /**
     * résout le problème du sac à dos en utilisant l'algorithme hill climbing
     * l'algorithme part d'une solution initiale et explore itérativement ses voisins en choisissant toujours le meilleur.
     * il s'arrête lorque:
     * Aucun voisin n'améliore la solution courante
     * Aucun voisin n'a la même utilité (si mode plateau désactivé)
     * Le nombre maximum de mouvements plateau est atteint (si mode plateau activé)
     * Aucun voisin ne peut être généré
     *
     * @param solution : solution initiale à partir de laquelle commencer la rechercher (doit être réalisable et non null)
     * @return la meilleur solution trouvée (optimum local)
     */
    public Solution resoudre(Solution solution) {
        if (solution == null) {
            throw new IllegalArgumentException("La solution initiale ne peut pas être null");
        }
        
        long debut = System.currentTimeMillis(); // mesurer le temps d'exec pr comparer les performances

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

    /**
     * parcourt tous les voisins, calcule leur utilité et retourne celui ayant l'utilité max.
     * l'utilité calculée est sauvegardée dans chaque solution pour éviter les recalculs.
     * @param voisins : liste des solutions voisines à évaluer (ne doit pas être vide)
     * @return solution ayan l'utilité max
     */
    private Solution trouverMeilleur(List<Solution> voisins) {
        Solution meilleur = voisins.get(0); //On suppose que le premier voisin est le meilleur
        double meilleureValeur = calculerUtilite(meilleur);//on calcule son utilité
        meilleur.setValeur(meilleureValeur);//on sauvegarde la valeur dans Solution car "Solution meilleur"
        //je parcours le reste des voisins
        for (int i = 1; i < voisins.size(); i++) {
            Solution voisin = voisins.get(i); //on récup chque voisin
            double valeur = calculerUtilite(voisin);//on recalcule l'utilite de chaque voisin
            voisin.setValeur(valeur); //et on le met à jour dans voisin

            if (valeur > meilleureValeur) {
                meilleur = voisin;
                meilleureValeur = valeur;
            }
        }

        return meilleur;
    }

    /**
     * Calculer l'utilité d'une solution: la somme des utilités de tous les objets qu'elle contient.
     * cette valeur représente la fonction objectif à maximiser dans le problème du sac à dos.
     * @param solution la solution dont l'utilité doit être calculée
     * @return l'utilité totale de la solution
     */
    private double calculerUtilite(Solution solution) {
        double total = 0.0;
        List<Objet> objets = sacADos.getObjets();
        for (int indice : solution.getObjets()) {
            total += objets.get(indice).getUtilite();
        }
        return total;
    }

    /**
     *
     * @return nbr d'itération de la dernière exécution de resoudre()
     */
    public int getIterations() {
        return iterations;
    }

}
