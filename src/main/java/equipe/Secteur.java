package equipe;

public enum Secteur {
    SPORT("Sport"),
    SANTE("Santé"),
    EDUCATION("Education"),
    CULTURE("Culture"),
    ATTRACTIVITE_ECONOMIQUE("Attractivité économique");

    private String libelle;

    Secteur(String nom){
        this.libelle = nom;
    }

    public String getLibelle(){
        return libelle;
    }

}
