package equipe;

/**
 * interface définissant le comportement d'un élu dans notre système.
 * un élu évalue les bénéfices potentiels d'un projet.
 */
public interface IElu {
    /**
     * évaluer et détermine le bénéfice d'un projet
     * @param p : projet à évaluer
     */
    void evaluerBenefice(Projet p);
}
