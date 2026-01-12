package solveur.hillclimbing;

import sacados.Objet;
import sacados.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * représente une solution candidate pour le problème
 */

public class Solution {
    private final Set<Integer> objets; //indices d'objets sélectionnées dans la solution
    private double valeur;

    /**
     * constructeur d'une solution à partir d'un ensemble d'indices d'objets
     * @param objets
     */
    public Solution(Set<Integer> objets) {
        if (objets == null) {
            throw new IllegalArgumentException("L'ensemble des objets ne peut pas être null");
        }
        this.objets = new HashSet<>(objets);
        this.valeur = 0.0;
    }

    /**
     *
     * @return ensemble des incides d'objets
     */
    public Set<Integer> getObjets() {
        return new HashSet<>(objets);
    }

    /**
     * définit la fonction objectif de la solution
     * @param valeur val associée à la solution
     */
    public void setValeur(double valeur) {
        this.valeur = valeur;
    }
    public double getValeur() {
        return valeur;
    }

    /**
     * compare cette solution à un autre objet
     * @param o   objet à comparer
     * @return true si les solutions sont égales, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return objets.equals(solution.objets);
    }

    /**
     *
     * @return hash code de la solution
     */
    @Override
    public int hashCode() {
        return objets.hashCode();
    }

    @Override
    public String toString() {
        return "Solution{" +
                "objets=" + objets +
                ", valeur=" + valeur +
                '}';
    }
    public void calculerValeur(SacADos sac) {
        double total = 0;
        for (int indice : objets) {
            total += sac.getObjets().get(indice).getUtilite();
        }
        this.valeur = total;
    }


    /**
     * vérifie si la solution respecte le budget
     * la méthode additionne les coûts de chaque objet séléctionné et vérifie qu'aucune contraintre
     * de budget n'est dépassé
      * @param probleme
     * @return true si la solution respecte les contraintes de budget, false sinon
     */
    public boolean respecteBudget(SacADos probleme) {
        if (probleme == null) {
            throw new IllegalArgumentException("Le problème ne peut pas être null");
        }
        int[] somme = new int[probleme.getBudget().length];
        List<Objet> tousLesObjets = probleme.getObjets();

        for (int indice : this.objets) {  // this.objets est le Set<Integer>
            Objet obj = tousLesObjets.get(indice);
            int[] couts = obj.getCouts();

            for (int i = 0; i < somme.length; i++) {
                somme[i] += couts[i];
            }
        }

        int[] budgets = probleme.getBudget();
        for (int i = 0; i < somme.length; i++) {
            if (somme[i] > budgets[i]) {
                return false;
            }
        }
        return true;
    }
}
