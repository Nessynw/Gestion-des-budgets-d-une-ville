package sacados;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Classe SacADos pour définir un objet de type SacADos.
 * Contient des méthodes pour
 * - afficher un sac à dos
 */
public class SacADos {
    private int dimension; //nb d'objets
    private int[] budget;
    private List<Objet> objets;
    private int valOptimale;//valeur optimale de l'utilité d'une solution, vaut 0 si inconnue
    
    public SacADos() {
        this.dimension = 0;
        this.budget = new int[0];
        this.objets = new ArrayList<>();
        this.valOptimale=0;
    }
    public SacADos(int[] B, List<Objet> ob) {
        if (B == null || ob == null) {
            throw new IllegalArgumentException("Les paramètres ne peuvent pas être null");
        }
        dimension = ob.size();
        budget = Arrays.copyOf(B, B.length);
        objets = new ArrayList<>(ob);
        valOptimale=0;
    }
    public SacADos(int[] B, List<Objet> ob,int valOp) {
        if (B == null || ob == null) {
            throw new IllegalArgumentException("Les paramètres ne peuvent pas être null");
        }
        dimension = ob.size();
        budget = Arrays.copyOf(B, B.length);
        objets = new ArrayList<>(ob);
        valOptimale=valOp;
    }
    public int getDimension() {
        return dimension;
    }
    public int[] getBudget() {
        return Arrays.copyOf(budget, budget.length);
    }
    public List<Objet> getObjets() {
        return new ArrayList<>(objets);
    }
    public int getValOptimale() {
        return valOptimale;
    }

    public void setBudget(int[] budget) {
        if (budget == null) {
            throw new IllegalArgumentException("Le budget ne peut pas être null");
        }
        this.budget = Arrays.copyOf(budget, budget.length);
    }

    public void afficheSac() {
        System.out.println("Budgets disponibles : " + Arrays.toString(budget));
        System.out.println("Contenu du sac :");
        for (Objet obj : objets) {
            System.out.println(obj);
        }
    }
}