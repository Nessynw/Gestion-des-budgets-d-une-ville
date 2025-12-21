package sacados;

import equipe.Projet;
import equipe.Secteur;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TestVersSacADos {
    private VersSacADos vsacUnderTest=new VersSacADos();

    @AfterEach
    public void clearSac(){
        vsacUnderTest=null;
    }

    public Budget creationBudget(){
        Budget budget=new Budget();
        int[] c={2,15,12};
        budget.setBudgetCouts(c);
        return budget;
    }

    public List<Projet> creationProjets(){
        List<Projet> projets=new ArrayList<>();
        for(int i=0;i<3;i++){
            Projet projettest=new Projet("Objet"+i,"test de conversion", Secteur.SPORT);
            projettest.setCoutEconomique(2*i+1);
            projettest.setCoutSocial(2*i+2);
            projettest.setCoutEnvironnemental(i+3);
            projettest.setBenefice(10*(i+1));
            projets.add(projettest);
        }
        return projets;
    }

    public List<Objet> creationObjets(){
        List<Objet> objets=new ArrayList<>(); //creation objets pour comparer avec les projets
        for(int i=0;i<3;i++){
            Projet projettest=creationProjets().get(i);
            int[] couts=new int[3];
            couts[0]=(int)projettest.getCoutEconomique();
            couts[1]=(int)projettest.getCoutSocial();
            couts[2]=(int)projettest.getCoutEnvironnemental();
            Objet objettest=new Objet(projettest.getTitre(),(int)projettest.getBenefice(),couts);
            objets.add(objettest);
        }
        return objets;
    }

    @Test
    public void conversionParCouts_budgetEtListDeProjets_SacADos(){
        Budget budget=creationBudget();
        List<Projet> projets=creationProjets();

        List<Objet> objets=creationObjets(); //objets pour comparer avec les projets

        SacADos sac=vsacUnderTest.conversionParCouts(budget,projets);
        assertEquals(sac.getDimension(),projets.size());
        assertArrayEquals(sac.getBudget(),budget.getBudgetCouts());
        assertEquals(sac.getObjets(),objets); //chaque objet est identique et ds le meme ordre

    }

    @Test
    public void convertir_nomFichier_SacADos(){
        String filename="/test.dat";
        SacADos sac=vsacUnderTest.convertir(filename);
        assertEquals(3,sac.getDimension());
        assertArrayEquals(sac.getBudget(),creationBudget().getBudgetCouts());
        assertEquals(sac.getObjets(),creationObjets());
    }
}
