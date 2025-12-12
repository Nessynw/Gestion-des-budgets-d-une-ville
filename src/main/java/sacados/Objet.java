package sacados;
import java.util.Arrays;

public class Objet{
    private String nom;
    private int utilite;
    private int[] couts;

    public Objet(String nom,int utilite,int[] couts){
        this.nom=nom;
        this.utilite=utilite;
        this.couts=couts;
    }

    public Objet(String nom,int utilite){
        this.nom=nom;
        this.utilite=utilite;
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

}

