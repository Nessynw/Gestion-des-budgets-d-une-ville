package equipe;
/**
 * Interface définissant le comportement d'un expert dans le système de gestion de projets.
 * Cette interface garantit que toute classe implémentant le rôle d'expert peut
 * créer et soumettre des projets pour évaluation par l'équipe municipale.
 */

public interface IExpert {
    /**
     *
     * @param titre
     * @param description
     * @param secteur
     * @return un nouveau projet
     */
    Projet proposerProjet(String titre, String description, Secteur secteur);
}
