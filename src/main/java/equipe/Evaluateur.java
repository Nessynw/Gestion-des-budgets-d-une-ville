package equipe;

/**
 * représente un évaluateur spécialisé dans l'estimation des coûts d'une projet.
 */

public class Evaluateur extends Personne implements IEvaluateur{

    private final TypeCout specialisation;

    /**
     * constructeur d'un évaluateur
     * @param nom
     * @param prenom
     * @param adresse
     * @param telephone
     * @param email
     * @param specialisation (social, économique, énvironnemental)
     */
    public Evaluateur(String nom, String prenom, String adresse, String telephone, String email, TypeCout specialisation) {
        super(nom,prenom,adresse,telephone,email);
        this.specialisation=specialisation;
    }

    /**
     * évalue un projet en lui attribuant un coût dans le domaine de spécialisation de l'évaluateur
     * elle génére une valeur aléatoire (dans [0-1000[) et l'attribue au projet selon la spécialisation.
     * @param p : projet à évaluer ( non null)
     */
    @Override
    public void evaluer(Projet p) {
        double valeur = Math.random()*1000;
        switch (specialisation){
            case SOCIAL -> p.setCoutSocial(valeur);
            case ECONOMIQUE -> p.setCoutEconomique(valeur);
            case ENVIRONNEMENTAL -> p.setCoutEnvironnemental(valeur);
        }

    }

    /**
     *
     * @return la spécialisation de l'évaluateur
     */

    public TypeCout getSpecialisation() {
        return specialisation;
    }
}
