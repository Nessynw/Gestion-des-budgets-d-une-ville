package sacados;

import java.util.Arrays;
import java.util.Scanner;

public class Budget{
    private int budgetTotal;
    private int[] budgetCouts;
    private int[] budgetSecteurs;
    public Budget(){
        budgetTotal =0;
        budgetCouts=new int[3];
        budgetSecteurs = new int[5];
    }
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
    public void setBudgetCouts(){//quand l'utilisateur entre le budget
        Scanner scanner = new Scanner(System.in);
        int[] bCouts=new int[3];
        System.out.println("Budgets selon les coûts:\n");
        System.out.println("Coût écomonique="); //en supposant qu'on met les couts dans cet ordre
        bCouts[0]= scanner.nextInt();
        System.out.println("\nCoût social=");
        bCouts[1]= scanner.nextInt();
        System.out.println("\nCoût environnemental=");
        bCouts[2]= scanner.nextInt();
        this.budgetCouts=bCouts;
    }
    public void setBudgetCouts(int[] budgetCouts) {//quand le budget est récupéré à partir d'un fichier
        this.budgetCouts = budgetCouts;
    }
    public void setBudgetSecteurs(){
        Scanner scanner = new Scanner(System.in);
        int[] bSecteurs =new int[5];
        System.out.println("Budgets selon les secteurs:\n"); //en supposant qu'on met les secteurs dans cet ordre
        System.out.println("Sport=");
        bSecteurs[0]= scanner.nextInt();
        System.out.println("\nSanté=");
        bSecteurs[1]= scanner.nextInt();
        System.out.println("\nEducation=");
        bSecteurs[2]= scanner.nextInt();
        System.out.println("\nCulture=");
        bSecteurs[3]= scanner.nextInt();
        System.out.println("\nAttractivité économique=");
        bSecteurs[4]= scanner.nextInt();
        this.budgetSecteurs= bSecteurs;
    }
    public void setBudgetSecteurs(int[] budgetSecteurs) {
        this.budgetSecteurs = budgetSecteurs;
    }
    public void setBudgetTotal(){ //quand l'utilisateur entre le budget
        System.out.println("Budget total:\n");
        Scanner scanner = new Scanner(System.in);
        this.budgetTotal=scanner.nextInt();
    }
    public void setBudgetTotal(int budgetTotal) {//quand le budget est récupéré à partir d'un fichier
        this.budgetTotal = budgetTotal;
    }
    public void afficheBudget(){
        System.out.println("Le budget restant est "+budgetTotal);
    }
    public boolean budgetCorrect(){
        int sommeCouts=budgetCouts[0]+budgetCouts[1]+budgetCouts[2];
        if(sommeCouts!=budgetTotal){
            System.out.println("Le budget total ne correspond pas aux budgets de chaque coûts");
            return false;
        }

        int sommeSecteurs =budgetSecteurs[0]+budgetSecteurs[1]+budgetSecteurs[2];
        if(sommeSecteurs !=budgetTotal){
            System.out.println("Le budget total ne correspond pas aux budgets de chaque secteurs");
            return false;
        }

        System.out.println("Aucune erreur lors de la saisie des budgets!");
        return true;
    }
}