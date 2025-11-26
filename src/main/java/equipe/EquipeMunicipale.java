package equipe;
import java.util.List;
import java.util.ArrayList;

public class EquipeMunicipale {
    private final Elu elu;
    private final Evaluateur evalEnv;
    private final Evaluateur evalSoc;
    private final Evaluateur evalEco;
    private final List<Expert> experts;
    private final List<Projet> projetsEtudies;

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

    public void ajouterExpert(Expert expert) {
        if (expert == null) {
            throw new IllegalArgumentException("L'expert ne peut pas être null");
        }
        experts.add(expert);
    }

    public void cycleSimulation() {
        for (Expert expert : experts) {
            Secteur secteurExpert = expert.getCompetences().get(0);
            String titre = "Projet " + expert.getNom();
            Projet projet = expert.proposerProjet(titre, "Description du " + titre, secteurExpert);

            evalEnv.evaluer(projet);
            evalSoc.evaluer(projet);
            evalEco.evaluer(projet);

            elu.evaluerBenefice(projet);
            projetsEtudies.add(projet);
        }
    }

    public List<Projet> getProjetsEtudies() {
        return new ArrayList<>(projetsEtudies);
    }

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
