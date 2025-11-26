package equipe;

public class Evaluateur extends Personne implements IEvaluateur{

    private TypeCout specialisation;
    public Evaluateur(String nom, String prenom, String adresse, String telephone, String email, TypeCout specialisation) {
        super(nom,prenom,adresse,telephone,email);
        this.specialisation=specialisation;
    }

    @Override
    public void evaluer(Projet p) {
        double valeur = Math.random()*10000;
        switch (specialisation){
            case SOCIAL -> p.setCoutSocial(valeur);
            case ECONOMIQUE -> p.setCoutEconomique(valeur);
            case ENVIRONNEMENTAL -> p.setCoutEnvironnemental(valeur);
        }

    }

    public TypeCout getSpecialisation() {
        return specialisation;
    }
}
