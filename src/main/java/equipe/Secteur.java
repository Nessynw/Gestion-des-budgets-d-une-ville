package equipe;

/**
 * énumération des différents secteurs d'activité pour les projets municipaux.
 */
public enum Secteur {
    SPORT("Sport"),
    SANTE("Santé"),
    EDUCATION("Education"),
    CULTURE("Culture"),
    ATTRACTIVITE_ECONOMIQUE("Attractivité économique");

    private String libelle;

    /**
     * constructeur d'une valeur d'énumération avec libellé spécifié
     * @param nom
     */
    Secteur(String nom){
        this.libelle = nom;
    }
    

}
