package sacados;
import java.util.Arrays;
import java.util.Objects;

public class Objet{
    private String nom;
    private int utilite;
    private int[] couts;

    public Objet(String nom,int utilite,int[] couts){
        this.nom=nom;
        this.utilite=utilite;
        this.couts=couts;
    }

    public Objet(){
        this.nom="";
        this.utilite=0;
        this.couts=new int[]{};
    }

    public Objet(String nom,int utilite){
        this.nom=nom;
        this.utilite=utilite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setUtilite(int utilite) {
        this.utilite = utilite;
    }

    public int getUtilite() {
        return utilite;
    }

    public int[] getCouts() {
        return couts;
    }

    public void setCouts(int[] couts) {
        this.couts = couts;
    }

    public void addCout(int val){
        int[] c=new int[couts.length+1];
        for(int i=0;i< couts.length;i++){
            c[i]=couts[i];
        }
        c[couts.length]=val;
        couts=c;
    }


    @Override
    public String toString(){
        return nom+"(utilité="+utilite+")(coûts="+Arrays.toString(couts)+")";
    }

    @Override
    public boolean equals(Object o) { //pour les tests junit
        if (this == o){ //les 2 references pointent vers le meme objet(cas trivial)
            return true;
        }

        if (o == null || getClass() != o.getClass()){//si les 2 ne font pas partie de la meme classe
            return false;
        }

        Objet objet = (Objet) o; //on redéfinit o comme un objet de type Objet et non pas Object
        return (utilite == objet.utilite)&&(Objects.equals(nom, objet.nom))&&(Arrays.equals(couts, objet.couts));
    }

    @Override
    public int hashCode() { //méthode qui accompagne le equals
        int result = Objects.hash(nom, utilite);//prend les valeurs et crée un hash associé
        result = 31 * result + Arrays.hashCode(couts);//on combine le hash précédent avec celui pour couts
        return result; //code de hachage unique pour l'objet
    }

}

