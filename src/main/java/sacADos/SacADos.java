package sacADos;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class SacADos {
    private int dimension; //nb d'objets
    private int[] budget;
    private List<Objet> objets;

    public SacADos() {
        this.dimension = 0;
        this.budget = new int[0];
        this.objets = new ArrayList<>();
    }

    public SacADos(int[] B, List<Objet> ob) {
        if (B == null || ob == null) {
            throw new IllegalArgumentException("Les paramètres ne peuvent pas être null");
        }
        dimension = ob.size();
        budget = Arrays.copyOf(B, B.length);
        objets = new ArrayList<>(ob);
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

    public void setBudget(int[] budget) {
        if (budget == null) {
            throw new IllegalArgumentException("Le budget ne peut pas être null");
        }
        this.budget = Arrays.copyOf(budget, budget.length);
    }

    public void addObjet(Objet o){
        if(o==null){
            throw new IllegalArgumentException("L'objet ne peut pas être null");
        }
        dimension++;
        objets.add(o);
    }

    public void addObjet(int index,Objet o){
        if(o==null){
            throw new IllegalArgumentException("L'objet ne peut pas être null");
        }
        if (index<0 || index> objets.size()){
            throw new IndexOutOfBoundsException("index invalide");
        }

        dimension++;
        objets.add(index,o);
    }

    public void removeObjet(Objet o){
        if(objets.remove(o)){
            dimension--;
        }
    }

    public void removeObjet(int index){
        if (index < 0 || index >= objets.size()) {
            throw new IndexOutOfBoundsException("Index invalide ");
        }

        objets.remove(index);
        dimension--;
    }

    public void afficheSac() {
        System.out.println("Budgets disponibles : " + Arrays.toString(budget));
        System.out.println("Contenu du sac :");
        for (Objet obj : objets) {
            System.out.println(obj);
        }
    }
}