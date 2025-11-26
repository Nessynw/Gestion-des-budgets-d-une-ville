package equipe;

import java.util.ArrayList;
import java.util.List;

public class Expert extends Personne implements IExpert{
    private final List<Secteur> competences;

    public Expert(String nom, String prenom, String address, String telephone, String email, List<Secteur> competences) {
        super(nom,prenom,address,telephone,email);
        this.competences=competences;
    }
    public List<Secteur> getCompetences() {
        return new ArrayList<>(competences);
    }

    @Override
    public Projet proposerProjet( String titre, String description, Secteur secteur) {
        if (!competences.contains(secteur)) {
            throw new IllegalArgumentException("Expert non compétent pour ce secteur");
        }
        return new Projet(titre, description, secteur);
    }

    @Override
    public String toString() {
        return super.toString() + " (Compétences: " + competences + ")";
    }
}
