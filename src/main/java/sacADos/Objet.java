package sacADos;
import java.util.Arrays;

public class Objet{
    private String nom;
    private int utilite;
    private int[] couts;

    public Objet(String nom, int utilite) {
    if (nom == null) {
        throw new IllegalArgumentException("Le nom ne peut pas être null");
    }
    this.nom = nom;
    this.utilite = utilite;
    this.couts = new int[0];
}

public Objet(String nom, int utilite, int[] couts) {
    if (nom == null) {
        throw new IllegalArgumentException("Le nom ne peut pas être null");
    }
    if (couts == null) {
        throw new IllegalArgumentException("Le tableau des coûts ne peut pas être null");
    }
    this.nom = nom;
    this.utilite = utilite;
    this.couts = Arrays.copyOf(couts, couts.length);
}

    public int getUtilite() {
        return utilite;
    }

    public int[] getCouts() {
    return Arrays.copyOf(couts, couts.length); // Retourne une copie
}

    public void addCout(int val) {
        int[] c = Arrays.copyOf(couts, couts.length + 1);
        c[couts.length] = val;
        couts = c;
    }

    @Override
    public String toString(){
        return nom+"(utilité = "+utilite+")(coûts ="+Arrays.toString(couts)+")";
    }

    public String getNom() {
        return nom;
    }
}

