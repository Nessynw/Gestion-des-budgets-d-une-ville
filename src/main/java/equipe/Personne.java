package equipe;

public abstract class Personne {
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;

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
}
