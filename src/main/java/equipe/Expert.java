package equipe;

import java.util.ArrayList;
import java.util.List;

/**
 * représente un expert capable de proposer des projets dans ses domaines de compétences.
 * Chaque expert maintient une liste de ses secteurs de spécialisation qui détermine les
 * types de projets qu'il peut proposer.
 */

public class Expert extends Personne implements IExpert{
    private final List<Secteur> competences; //liste des secteurs dans lesquels l'expert possède des compétences

    /**
     * constructeur d'un expert
     * @param nom
     * @param prenom
     * @param address
     * @param telephone
     * @param email
     * @param competences
     */
    public Expert(String nom, String prenom, String address, String telephone, String email, List<Secteur> competences) {
        super(nom,prenom,address,telephone,email);
        this.competences=competences;
    }

    /**
     *
     * @return une nouvelle liste contenant tous les secteurs de compétences de l'expert
     */
    public List<Secteur> getCompetences() {
        return new ArrayList<>(competences);
    }

    /**
     * propose un nouveau projet dans un secteur donné
     * cette méthode permet à l'expert de proposer des projets uniquement si le secteur du projet
     * correpond à l'une de ses compétences.
     * @param titre
     * @param description
     * @param secteur : secteur d'activité du projet (doit être inclus dans la liste des compétences)
     * @return: nouveau projet
     */
    @Override
    public Projet proposerProjet( String titre, String description, Secteur secteur) {
        if (!competences.contains(secteur)) {
            throw new IllegalArgumentException("Expert non compétent pour ce secteur");
        }
        return new Projet(titre, description, secteur);
    }

    /**
     *
     * @returnune chaîne de caractères représentant l'expert.
     */
    @Override
    public String toString() {
        return super.toString() + " (Compétences: " + competences + ")";
    }
}
