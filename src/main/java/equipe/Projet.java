package equipe;


/**
 * représente un projet dans le sustème.
 */
public class Projet{
    private final String titre;
    private final String description;
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
        if (coutEconomique < 0) {
            throw new IllegalArgumentException("Le coût ne peut pas être négatif");
        }
        this.coutEconomique = coutEconomique;
    }

    public double getCoutSocial() {
        return coutSocial;
    }

    public void setCoutSocial(double coutSocial) {
        if(coutSocial<0){
            throw new IllegalArgumentException("Le coût ne peut pas être négatif");

        }
        this.coutSocial = coutSocial;
    }
    public double getCoutEnvironnemental() {
        return coutEnvironnemental;
    }
    public void setCoutEnvironnemental(double coutEnvironnemental) {
        if(coutEnvironnemental<0){
            throw new IllegalArgumentException("Le coût ne peut pas être négatif");

        }
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
     * @return une chaîne de caractères représentant le projet avec toutes ses évaluations
     */
    @Override
    public String toString() {
        return "Projet{" +
                "titre='" + titre + '\'' +
                ", secteur=" + secteur +
                ", bénéfice=" + benefice +
                ", coûts=[éco:" + coutEconomique +
                ", soc:" + coutSocial +
                ", env:" + coutEnvironnemental + "]" +
                '}';
    }

}