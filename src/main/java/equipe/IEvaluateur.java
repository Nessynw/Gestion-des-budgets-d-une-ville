package equipe;

/**
 * Interface définissant le comportement d'un évaluateur dans notre système.
 * Cette interface garantit que toute classe implémentant le rôle d'évaluateur fournit
 * une méthode pour réaliser cette évaluation d'un projet.
 */

public interface IEvaluateur {
    /**
     *
     * @param p
     */
    void evaluer(Projet p);
}

