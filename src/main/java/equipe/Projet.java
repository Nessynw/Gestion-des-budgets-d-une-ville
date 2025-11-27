package equipe;

public class Projet{
    private String titre;
    private String description;
    private final Secteur secteur;
    private double coutEconomique;
    private double coutSocial;
    private double coutEnvironnemental;
    private double benefice;



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
    public double getBenefice() {
        return benefice;
    }
    public void setBenefice(double benefice) {}

    @Override
    public String toString() {
        return String.format("%s (%s) - Bénéfice: %.2f, Coûts: éco=%.2f, soc=%.2f, env=%.2f",
                titre, secteur.getLibelle(), benefice, coutEconomique, coutSocial, coutEnvironnemental);
    }

}