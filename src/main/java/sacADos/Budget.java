package sacADos;

public class Budget{
    private int budgetTotal=0;
    private int[] budgetCouts;
    private int[] budgetSecteurs;

    public Budget(int budget,int[] c,int[] s){
        budgetTotal=budget;
        budgetCouts=c;
        budgetSecteurs=s;
    }

    public int getBudgetTotal() {
        return budgetTotal;
    }

    public int[] getBudgetCouts(){
        return budgetCouts;
    }

    public int[] getBudgetSecteurs(){
        return budgetSecteurs;
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
        System.out.println("Le budget restant est"+budgetTotal);
    }
}