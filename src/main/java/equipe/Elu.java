package equipe;

public class Elu extends Personne implements IElu{
    public Elu(String nom, String prenom, String adresse, String telephone, String email) {
        super(nom,prenom,adresse,telephone,email);
    }
    @Override
    public void evaluerBenefice(Projet p) {
        p.setBenefice(p.getBenefice()+1000);
    }
}
