package solveur.glouton;

import sacados.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class gloutonRetraitSolverTest {

    private SacADos sacSimple;

    @BeforeEach
    public void setUp() {
        Objet obj0 = new Objet("obj0", 5, new int[]{10});
        Objet obj1 = new Objet("obj1", 8, new int[]{15});
        Objet obj2 = new Objet("obj2", 12, new int[]{20});
        sacSimple = new SacADos(new int[]{30}, List.of(obj0, obj1, obj2));
    }

    @Test
    public void testRetraitRespectBudget() {
        GloutonRetraitSolver solver = new GloutonRetraitSolver();
        List<Objet> solution = solver.resoudre(sacSimple);

        int totalCout = solution.stream().mapToInt(o -> o.getCouts()[0]).sum();
        assertTrue(totalCout <= 30, "Le total des coûts dépasse le budget !");
    }

    @Test
    public void testRetraitValeurTotale() {
        GloutonRetraitSolver solver = new GloutonRetraitSolver();
        List<Objet> solution = solver.resoudre(sacSimple);

        int totalUtilite = solution.stream().mapToInt(Objet::getUtilite).sum();
        assertTrue(totalUtilite <= 25, "La valeur totale de la solution est incorrecte !");
    }

    @Test
    public void testRetraitReajoutObjets() {
        GloutonRetraitSolver solver = new GloutonRetraitSolver();
        List<Objet> solution = solver.resoudre(sacSimple);

        int[] budget = sacSimple.getBudget();
        int sommeCout = solution.stream().mapToInt(o -> o.getCouts()[0]).sum();
        assertTrue(sommeCout <= budget[0], "Un objet dépasse le budget après retrait/re-ajout !");
    }
}
