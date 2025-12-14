package equipe;

/**
 *Classe abstraite représentant une personne dans le système.
 * cette classe constitue la base pour tous les membres de l'équipe municipale
 * elle encapsule les information communes à toutes les personnes.
 *
 * En tant que classe abstraite, Personne ne peut pas être instanciée directement
 * et doit être étendue par des classes concrètes.
 */

public abstract class Personne {
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;

    /**
     * constructeur d'une personne
     * @param nom
     * @param prenom
     * @param adresse
     * @param telephone
     * @param email
     */
    public Personne(String nom, String prenom, String adresse, String telephone, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
    }


    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getAdresse() {
        return adresse;
    }
    public String getTelephone() {
        return telephone;
    }
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return prenom + " " + nom;
    }
}
