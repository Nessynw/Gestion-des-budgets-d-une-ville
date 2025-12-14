package sacados;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Classe Budget pour définir un objet de type Budget.
 * Contient des méthodes pour accéder/modifier/vérifier les valeurs des budgets.
 */
public class Budget{
    private int budgetTotal;
    private int[] budgetCouts;//budget disponible pour chacun des 3 coûts(économique/social/environnemental)
    private int[] budgetSecteurs;//budget disponible pour chacun des 5 secteurs
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

    /**
     * Attribue des valeurs aux constructeurs(aux différents budgets)
     * à partir des données saisies par l'utilisateur.
     */
    public void saisieBudget(){
        do{
            //saisir le budget total
            Scanner scanner = new Scanner(System.in);
            System.out.println("Budget total:\n");
            setBudgetTotal(scanner.nextInt());

            //saisir les budgets par coûts
            int[] bCouts=new int[3];
            System.out.println("Budgets selon les coûts:\n");
            System.out.println("Coût économique="); //en supposant qu'on met les couts dans cet ordre
            bCouts[0]= scanner.nextInt();
            System.out.println("\nCoût social=");
            bCouts[1]= scanner.nextInt();
            System.out.println("\nCoût environnemental=");
            bCouts[2]= scanner.nextInt();
            setBudgetCouts(bCouts);

            //saisir les budgets par secteurs
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
            setBudgetSecteurs(bSecteurs);
        }while(!this.budgetCorrect());
    }

    /**
     * Vérifie que les budgets sont corrects c'est-à-dire
     * si la somme des budgets par coûts(ou par secteurs) ne dépasse pas le budget total.
     * @return true si les budgets sont corrects, false sinon
     */
    public boolean budgetCorrect(){
        int sommeCouts=budgetCouts[0]+budgetCouts[1]+budgetCouts[2];
        int sommeSecteurs =budgetSecteurs[0]+budgetSecteurs[1]+budgetSecteurs[2]+budgetSecteurs[3]+budgetSecteurs[4];
        if(sommeCouts!=budgetTotal){
            System.out.println("Le budget total ne correspond pas aux budgets de chaque coût");
            return false;
        }
        else if(sommeSecteurs !=budgetTotal){
            System.out.println("Le budget total ne correspond pas aux budgets de chaque secteur");
            return false;
        }
        else{
            System.out.println("Aucune erreur lors de la saisie des budgets!");
            return true;
        }
    }
}