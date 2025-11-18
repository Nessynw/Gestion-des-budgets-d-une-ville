package sacADos;
import java.util.List;
import java.util.Arrays;

public class Sacados{
    private int dimension; //nb d'objets
    private int[] budget;
    private List<Objet> objets;

    public Sacados(int[] B,List<Objet> ob){
        dimension=ob.size();
        budget=B;
        objets=ob;
    }

    public int getDimension() {
        return dimension;
    }

    public int[] getBudget() {
        return budget;
    }

    public void setBudget(int[] budget) {
        this.budget = budget;
    }

    public List<Objet> getObjets() {
        return objets;
    }

    public void addObjet(Objet o){
        dimension++;
        objets.add(o);
    }

    public void addObjet(int index,Objet o){
        dimension++;
        objets.add(index,o);
    }

    public void removeObjet(Objet o){
        if(objets.remove(o)){
            dimension--;
        }
    }

    public void removeObjet(int index){
        objets.remove(index);
        dimension--;
    }

    public void afficheSac(){
        System.out.println("Nombres d'objets:"+dimension);
        System.out.println("Budgets:"+Arrays.toString(budget));
        System.out.println("Objets:"+objets.toString());
    }

}