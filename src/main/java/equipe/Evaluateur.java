package equipe;

import java.util.Random;

public class Evaluateur extends Personne{
   private TypeCout specialisation;
   private Random random;
    public Evaluateur(String nom, String prenom, String adresse, String telephone, String email, String role) {
        super(nom, prenom, adresse, telephone, email, role);
        this.specialisation=specialisation;
        this.random=new Random();
    }
    public TypeCout getSpecialisation() {
        return specialisation;
    }
    public void setSpecialisation(TypeCout specialisation) {}
}
