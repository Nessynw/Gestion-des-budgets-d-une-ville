package equipe;

/**
 * représente un élu, un membre de l'équipe municipale pouvant évaluer les bénéfices des projets.
 * cette classe étend Personne et implémente l'interface IElu
 */
public class Elu extends Personne implements IElu{
    /**
     * Constructeur d'un élu
     * @param nom
     * @param prenom
     * @param adresse
     * @param telephone
     * @param email
     */
    public Elu(String nom, String prenom, String adresse, String telephone, String email) {
        super(nom,prenom,adresse,telephone,email);
    }

    /**
     * méthode qui évalue et augmente le bénéfice d'un projet de 1000 unités.
     * @param p : le projet dont le bénéfice doit être évalué et augmenté ( ne peut pas être null)
     */
    @Override
    public void evaluerBenefice(Projet p) {
        p.setBenefice(p.getBenefice()+1000);
    }
}
