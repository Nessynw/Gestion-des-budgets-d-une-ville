package equipe;

/**
 * représente un projet dans le sustème.
 */
public class Projet{
    private String titre;
    private String description;
    private final Secteur secteur;
    private double coutEconomique;
    private double coutSocial;
    private double coutEnvironnemental;
    private double benefice;

    /**
     * constructeur d'un projet
     * @param titre
     * @param description
     * @param secteur: secteur d'activité du projet(ne pourra plus être modifiée après la création)
     */

    public Projet(String titre, String description, Secteur secteur) {
        this.titre = titre;
        this.description = description;
        this.secteur = secteur;
    }
    public String getTitre() {
        return titre;
    }
    public String getDescription() {
        return description;
    }
    public Secteur getSecteur() {
        return secteur;
    }
    public double getCoutEconomique() {
        return coutEconomique;
    }

    /**
     * définit le cout économique
     * méthode appelée par un évaluateur spécialisé en coût économique
     * @param coutEconomique
     */
    public void setCoutEconomique(double coutEconomique) {
        this.coutEconomique = coutEconomique;
    }

    public double getCoutSocial() {
        return coutSocial;
    }

    public void setCoutSocial(double coutSocial) {
        this.coutSocial = coutSocial;
    }
    public double getCoutEnvironnemental() {
        return coutEnvironnemental;
    }
    public void setCoutEnvironnemental(double coutEnvironnemental) {
        this.coutEnvironnemental = coutEnvironnemental;
    }

    /**
     *
     * @return le bénéfice estimé du projet
     */
    public double getBenefice() {
        return benefice;
    }

    /**
     * cette méthode est appelée par un élu lors de l'évaluation de la valeur globale ud projet
     * @param benefice
     */
    public void setBenefice(double benefice) {
        this.benefice=benefice;
    }

    /**
     *
     * @returnune chaîne de caractères représentant le projet avec toutes ses évaluations
     */
    @Override
    public String toString() {
        return String.format("%s (%s) - Bénéfice: %.2f, Coûts: éco=%.2f, soc=%.2f, env=%.2f",
                titre, secteur.getLibelle(), benefice, coutEconomique, coutSocial, coutEnvironnemental);
    }

}