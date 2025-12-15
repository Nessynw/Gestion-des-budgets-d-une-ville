package sacados;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Classe Budget pour définir un objet de type Budget.
 * Contient des méthodes pour accéder/modifier les valeurs des budgets.
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
     * - Attribue des valeurs aux constructeurs(aux différents budgets)
     * à partir des données saisies par l'utilisateur.
     * - Vérifie que les budgets sont corrects c'est-à-dire
     * si la somme des budgets par coûts(ou par secteurs) ne dépasse pas le budget total.
     */
    public void saisieBudget(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Budget total: ");
        setBudgetTotal(scanner.nextInt());

        System.out.println("\nChoisissez le mode de budgétisation:");
        System.out.println("1. Par coûts (économique, social, environnemental)");
        System.out.println("2. Par secteurs (sport, santé, éducation, culture, attractivité)");
        System.out.print("Votre choix (1 ou 2): ");
        int mode = scanner.nextInt();

        if (mode == 1) {
            // Saisir SEULEMENT les budgets par coûts
            int[] bCouts = new int[3];
            System.out.println("\nBudgets selon les coûts:");
            System.out.print("Coût économique = ");
            bCouts[0] = scanner.nextInt();
            System.out.print("Coût social = ");
            bCouts[1] = scanner.nextInt();
            System.out.print("Coût environnemental = ");
            bCouts[2] = scanner.nextInt();
            setBudgetCouts(bCouts);

            // Vérification
            int sommeCouts = bCouts[0] + bCouts[1] + bCouts[2];
            if (sommeCouts != budgetTotal) {
                System.out.println("Le budget total ne correspond pas aux budgets de chaque coût");
                saisieBudget();
                return;
            }

            // Initialiser budgetSecteurs à null ou [0,0,0,0,0]
            setBudgetSecteurs(new int[5]); // Mode par coûts = pas de secteurs

        } else {
            // Saisir SEULEMENT les budgets par secteurs
            int[] bSecteurs = new int[5];
            System.out.println("\nBudgets selon les secteurs:");
            System.out.print("Sport = ");
            bSecteurs[0] = scanner.nextInt();
            System.out.print("Santé = ");
            bSecteurs[1] = scanner.nextInt();
            System.out.print("Education = ");
            bSecteurs[2] = scanner.nextInt();
            System.out.print("Culture = ");
            bSecteurs[3] = scanner.nextInt();
            System.out.print("Attractivité économique = ");
            bSecteurs[4] = scanner.nextInt();
            setBudgetSecteurs(bSecteurs);

            // Vérification
            int sommeSecteurs = bSecteurs[0] + bSecteurs[1] + bSecteurs[2] + bSecteurs[3] + bSecteurs[4];
            if (sommeSecteurs != budgetTotal) {
                System.out.println("Le budget total ne correspond pas aux budgets de chaque secteur");
                saisieBudget();
                return;
            }

            // Initialiser budgetCouts à null ou [0,0,0]
            setBudgetCouts(new int[3]); // Mode par secteurs = pas de coûts détaillés
        }

        System.out.println("Aucune erreur lors de la saisie des budgets!");
    }

    /**
     * Vérifie que les budgets sont corrects c'est-à-dire
     * si la somme des budgets par coûts(ou par secteurs) ne dépasse pas le budget total.
     * @return true si les budgets sont corrects, false sinon
     */
    public boolean budgetCorrect(){
        // Déterminer quel mode est actif
        boolean modeCouts = (budgetSecteurs[0] == 0 && budgetSecteurs[1] == 0
                && budgetSecteurs[2] == 0 && budgetSecteurs[3] == 0
                && budgetSecteurs[4] == 0);

        if (modeCouts) {
            int sommeCouts = budgetCouts[0] + budgetCouts[1] + budgetCouts[2];
            if (sommeCouts != budgetTotal) {
                System.out.println("Le budget total ne correspond pas aux budgets de chaque coût");
                return false;
            }
        } else {
            int sommeSecteurs = budgetSecteurs[0] + budgetSecteurs[1] + budgetSecteurs[2]
                    + budgetSecteurs[3] + budgetSecteurs[4];
            if (sommeSecteurs != budgetTotal) {
                System.out.println("Le budget total ne correspond pas aux budgets de chaque secteur");
                return false;
            }
        }

        System.out.println("Aucune erreur lors de la saisie des budgets!");
        return true;
    }

}