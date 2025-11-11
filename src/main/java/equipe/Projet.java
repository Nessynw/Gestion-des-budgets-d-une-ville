package equipe;

public class Projet {
    private final Secteur secteur;
    private final int benefice;
    private String titre;
    private String description;
    private int coutEconomique;
    private int coutSocial;
    private int coutEnvironnemental;

    public Projet(String titre, String description, Secteur secteur) {
        this.titre = titre;
        this.description = description;
        this.secteur = secteur;
        this.benefice = 0;
        this.coutEconomique = 0;
        this.coutSocial = 0;
        this.coutEnvironnemental = 0;
    }



}
