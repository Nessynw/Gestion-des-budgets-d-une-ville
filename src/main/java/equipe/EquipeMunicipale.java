package equipe;
import java.util.List;
import java.util.ArrayList;

public class EquipeMunicipale {
    private Elu elu;
    private Evaluateur evalEnv;
    private Evaluateur evalSoc;
    private Evaluateur evalEco;
    private List<Projet> projets;
    private List<Expert> experts;

    public EquipeMunicipale(Elu elu, Evaluateur evalEnv, Evaluateur evalSoc, Evaluateur evalEco) {
        this.elu = elu;
        this.evalEnv = evalEnv;
        this.evalSoc = evalSoc;
        this.evalEco = evalEco;
        projets=new ArrayList<>();
        experts=new ArrayList<>();
    }

    public void cycleSimulation(){
        for ( Expert expert : experts){
            Projet p= expert.proposerProjet("Projet "+ Math.random(), "Description", Secteur.SANTE);
            evalEnv.evaluer(p);
            evalSoc.evaluer(p);
            evalEco.evaluer(p);
            elu.evaluerBenefice(p);
            projets.add(p);
        }
    }

}
