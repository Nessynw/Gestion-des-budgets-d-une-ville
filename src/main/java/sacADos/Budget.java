package sacADos;

import java.util.Arrays;

public class Budget{
    private int budgetTotal=0;
    private int[] budgetCouts;
    private int[] budgetSecteurs;

    public Budget(int budget, int[] c, int[] s){
    if (c == null || s == null) {
        throw new IllegalArgumentException("Les tableaux de budget ne peuvent pas être null");
    }
    budgetTotal = budget;
    budgetCouts = c;
    budgetSecteurs = s;
}

    public int getBudgetTotal() {
        return budgetTotal;
    }

    public int[] getBudgetCouts(){
    return Arrays.copyOf(budgetCouts, budgetCouts.length);//j'ai fais en sorte qu'on retourne des copies ici pour éviter de compromettre l'encapsulation
}

public int[] getBudgetSecteurs(){
    return Arrays.copyOf(budgetSecteurs, budgetSecteurs.length);
}

    public void setBudgetCouts(int[] budgetCouts) {
        this.budgetCouts = budgetCouts;
    }

    public void setBudgetSecteurs(int[] budgetSecteurs) {
        this.budgetSecteurs = budgetSecteurs;
    }

    public void setBudgetTotal(int budgetTotal) {
        this.budgetTotal = budgetTotal;
    }

    public void afficheBudget(){
        System.out.println("Le budget restant est "+budgetTotal);
    }
}