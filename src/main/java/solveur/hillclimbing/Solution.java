package solveur.hillclimbing;

import sacados.Objet;
import sacados.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    private final Set<Integer> objets;
    private double valeur;

    public Solution(Set<Integer> objets) {
        if (objets == null) {
            throw new IllegalArgumentException("L'ensemble des objets ne peut pas être null");
        }
        this.objets = new HashSet<>(objets);
        this.valeur = 0.0;
    }
    public Set<Integer> getObjets() {
        return new HashSet<>(objets);
    }
    public void setValeur(double valeur) {
        this.valeur = valeur;
    }
    public double getValeur() {
        return valeur;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return objets.equals(solution.objets);
    }
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
    // Dans Solution.java
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
