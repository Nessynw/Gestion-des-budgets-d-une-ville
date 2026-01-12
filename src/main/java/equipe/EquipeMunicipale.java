package equipe;
import java.util.List;
import java.util.ArrayList;

/**
 * représente une équipe municipale composée d'un élu, trois évaluateurs spécialisés, une liste d'experts
 * qui proposent des projets.
 * Le cycle de simulation permet de traiter tous les projets proposés en appliquant
 * l'ensemble des évaluations & en calculant les bénéfices.
 */
public class EquipeMunicipale {
    private final Elu elu;
    private final Evaluateur evalEnv;
    private final Evaluateur evalSoc;
    private final Evaluateur evalEco;
    private final List<Expert> experts;
    private final List<Projet> projetsEtudies;

    /**
     * constructeur d'une équipe municipale
     * il vérifie que chaque évaluateur possède la spécialisation approporiée.
     * Si ce n'est pas le cas : une exception est levée.
     * @param elu
     * @param evalEnv
     * @param evalSoc
     * @param evalEco
     * @throws IllegalArgumentException si évaluateur possède pas la spécialisation approporiée
     */
    public EquipeMunicipale(Elu elu, Evaluateur evalEnv, Evaluateur evalSoc, Evaluateur evalEco) {
        if (evalEnv.getSpecialisation() != TypeCout.ENVIRONNEMENTAL) {
            throw new IllegalArgumentException("L'évaluateur environnemental doit être spécialisé en coût environnemental");
        }
        if (evalSoc.getSpecialisation() != TypeCout.SOCIAL) {
            throw new IllegalArgumentException("L'évaluateur social doit être spécialisé en coût social");
        }
        if (evalEco.getSpecialisation() != TypeCout.ECONOMIQUE) {
            throw new IllegalArgumentException("L'évaluateur économique doit être spécialisé en coût économique");
        }

        this.elu = elu;
        this.evalEnv = evalEnv;
        this.evalSoc = evalSoc;
        this.evalEco = evalEco;
        this.experts = new ArrayList<>();
        this.projetsEtudies = new ArrayList<>();
    }

    /**
     * ajouter un expert à l'équipe municipale, il pourra ensuite proposer des projets lors du cycle de simulation.
     * @param expert : expert à ajouter (ne peut pas être null)
     */

    public void ajouterExpert(Expert expert) {
        if (expert == null) {
            throw new IllegalArgumentException("L'expert ne peut pas être null");
        }
        experts.add(expert);
    }

    /**
     * exécute un cycle complet de simulation de proposition de projets et d'évaluation.
     * Pour chaque expert :
     * la méthode récupère la compétence de l'expert comme secteur du projet
     * demande à l'expert de proposer un projet dans ce secteur
     * appliquer les trois évaluateurs l'un après l'autre
     * fait évaluer le bénéfice du projet par l'élu
     * Ajoute le projet à la liste des projets étudiés.
     */

    public void cycleSimulation() {
        for (Expert expert : experts) {
            for (Secteur secteur : expert.getCompetences()){
            String titre = "Projet " + secteur + expert.getNom();
            Projet projet = expert.proposerProjet(titre, "Description du " + titre, secteur);

            evalEnv.evaluer(projet);
            evalSoc.evaluer(projet);
            evalEco.evaluer(projet);

            elu.evaluerBenefice(projet);

            projetsEtudies.add(projet);

        }}
    }

    /**
     *
     * @return une copie de la liste des projets étudiés par l'équipe municipale
     * cette méthode retourne une nouvelle liste, c'est pour préserver l'encapsulation et
     *et éviter toute modification externes
     */

    public List<Projet> getProjetsEtudies() {
        return new ArrayList<>(projetsEtudies);
    }

    /**
     *
     * @return une chaine de caractères représentant l'équipe municipale
     */
    @Override
    public String toString() {
        return "EquipeMunicipale{" +
                "\n  Élu=" + elu +
                "\n  Évaluateur Environnemental=" + evalEnv +
                "\n  Évaluateur Social=" + evalSoc +
                "\n  Évaluateur Économique=" + evalEco +
                "\n  Experts=" + experts +
                "\n  Projets étudiés=" + projetsEtudies +
                "\n}";
    }
}