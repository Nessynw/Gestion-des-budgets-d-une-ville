package equipe;

public class Projet extends Objet {
    private final Secteur secteur;
    private final int benefice;
    private String titre;
    private String description;
    private int[] couts
    /*private int coutEconomique;
    private int coutSocial;
    private int coutEnvironnemental;*/

    public Projet(String titre, String description, Secteur secteur,int benefice,int[] couts) {
        super(titre,benefice,couts)
        //this.titre = titre;
        this.description = description;
        this.secteur = secteur;
        /*this.benefice = 0;
        this.coutEconomique = 0;
        this.coutSocial = 0;
        this.coutEnvironnemental = 0;*/
    }



}
